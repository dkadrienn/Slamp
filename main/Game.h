//Effektek hasznalata: - Dice: Ez az effekt eloszor a jatekosok szamat varja amely [1,6] lehet
//                             A jatekosok megadasa utan meg kell adjuk, hogy az adott jatekosra
//                             mely tortenes vonatkozi : 0-as ertek -> a jatekos kimarad
//                                                       1-es ertek -> a jatekos dob
//                                                       6-os ertek -> a jatekos megegyszer dobhat
//                             Ebbol az effektbol a 2-es karakter elkuldesevel lehet kilepni
//                     -Timer: Ez az effekt egy egesz szam megadasat varja, majd a megadott szamnak
//                             megfelelo masodperc alatt felgyul az osszes led.
//                             Ebbol az effektel akkor lehet kilepni ha 0-nal kisebb erteket adunk meg
#include <Adafruit_NeoPixel.h>

class Game
{
  private:
    int NUM_LEDS;
    Adafruit_NeoPixel strip;
    typedef struct colors {
      int red, green, blue;
    };

    colors figures[6] = {colors{255, 0, 0}, colors{0, 255, 0},
                         colors{0, 0, 255}, colors{255, 255, 0},
                         colors{255, 0, 255}, colors{0, 255, 255}
                        };

    char Game_Rdata;
    char numberOfPlayersChar;
    int block = 1;

  public:
    Game(int NUM_LEDS, Adafruit_NeoPixel strip) {
      this->NUM_LEDS = NUM_LEDS;
      this->strip = strip;
    }
    //Dice
    void dice() {
      Serial.println("DICE");
      int numberOfPlayers = -1;
      int actFigure = 0;
      char numberOfPlayersChar = 0x00;
      Serial.print("Jatekosok szama: ");
      if  (block == 1) { // A jatekosok szamanak beolvasasa
        while (numberOfPlayers < 0) {
          delay(100);
          numberOfPlayersChar = Serial.read();
          numberOfPlayers = numberOfPlayersChar - '0';
        }

        Serial.println(numberOfPlayers);
        block --;
      }
      while (Game_Rdata = Serial.read()) {
        if (Game_Rdata == '1') { //Egyest olvasva dob a kovetkezo jatekos
          throwing(actFigure);
          if ( actFigure == numberOfPlayers - 1 ) {
            actFigure = 0;
          }
          else {
            actFigure ++;
          }
        }
        else if (Game_Rdata == '6')
        {
          actFigure --;
          throwing(actFigure);
          if ( actFigure == numberOfPlayers - 1 ) {
            actFigure = 0;
          }
          else {
            actFigure ++;
          }
        }
        else if (Game_Rdata == '0') { //Kimarad egy korbol ha zeros-t olvas
          Serial.print(actFigure + 1);
          Serial.println(". kimarad");
          if ( actFigure == numberOfPlayers - 1 ) {
            actFigure = 0;
          }
          else {
            actFigure ++;
          }
        }
        else if (Game_Rdata == '2') {
          Serial.println("KILEPES");
          block ++;
          return;
        }
      }
      return ;
    }

    void throwing(int actFigure) {
      int diceValue = random(1, 7);
      Serial.print(actFigure + 1);
      Serial.print(". figura dobasa: ");
      Serial.println(diceValue);
      for ( int flashNr = 0; flashNr < diceValue; flashNr ++) {
        for (int actLed = 0; actLed < NUM_LEDS; actLed++) {
          strip.setPixelColor(actLed, strip.Color(figures[actFigure].red,
                                                  figures[actFigure].green,
                                                  figures[actFigure].blue));
          strip.show();
        }
        delay(500);
        for (int actLed = 0; actLed < NUM_LEDS; actLed++) {
          strip.setPixelColor(actLed, strip.Color(0, 0, 0));
          strip.show();
        }
        delay(500);
      }
      return ;
    }

    //Timer effect
    void timer() {
      Serial.println("TIMER");
      int timer_time;
      while (1) {
        for (int i = 0; i < NUM_LEDS; i++) {
          strip.setPixelColor(i, strip.Color(0, 0, 0));
          strip.show();
        }
        Serial.print("Hany sec: ");
        timer_time = Serial.parseInt();
        if (timer_time > 0) {
          Serial.println(timer_time);
          timer_fill(timer_time);
          blink();
        }
        else if (timer_time < 0) {
          return ;
        }
      }
      return ;
    }

    void timer_fill(int timeSec) {
      //Serial.println(NUM_LEDS);
      float delayVal = (double)timeSec / ((double)NUM_LEDS);
      //Serial.println(delayVal);
      for (int actled = 0; actled < NUM_LEDS ; actled++) {
        Serial.println("HOPP");
        if ( 255 - (actled * 10) < 0 ) {
          strip.setPixelColor(actled, strip.Color( 255, 0, 0));
        }
        else {
          strip.setPixelColor(actled, strip.Color( actled * 10, 255 - (actled * 10), 0));
        }
        strip.show();
        delay(delayVal * 1000); // késleltetés
      }
      return;
    }

    void blink() {
      // Kikapcsol
      int howmanytimes = 3;
      while (howmanytimes > 0) {
        Serial.println("BLINK");
        for ( int i = 0; i < NUM_LEDS; i++) {
          strip.setPixelColor(i, strip.Color(255, 0, 0));
          strip.show();
        }
        delay(1000);
        for (int i = 0; i < NUM_LEDS; i++) {
          strip.setPixelColor(i, strip.Color(0, 0, 0));
          strip.show();
        }
        delay(1000);
        howmanytimes--;
      }
      return ;
    }

};
