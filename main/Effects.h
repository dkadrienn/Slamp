//Effektek hasznalata: - Disco, Christmass, Raindrop, Fireplace, Romantic, Rainbow, Hourglass,
//                        Smooth_White, Smooth_Random, Cycle effektek addig futnak ameddig a bementen
//                        egy '0'-as nem erkezik, ekkor visszaternak a mainbe es egy uj effekt inditasara vaarnak
//                     - A myBRBG effekt egy szin r, g, b komponenseinek megadasat es a brightness beallitasat
//                       varja. Az effekt akkor fejezodik be, ha az r komponensnek negaiv szamot adunk
//                       !!! Az r,g,b, brighness komponenseknek nem tudunk 0-s erteket adni !!!

#include <Adafruit_NeoPixel.h>

class Effects
{
  private:
    int NUM_LEDS;
    Adafruit_NeoPixel strip;
    int colorArray[7][3] = {{1, 1, 1}, {1, 0, 0}, {0, 1, 0}, {0, 0, 1},
      {1, 1, 0}, {1, 0, 1}, {0, 1, 1}
    };
    int actPos = 0;
    int red, blue, green, direction = 1;
    char RdataE;
    int exitStatus = 0;

  public:
    //Constructor
    Effects(int NUM_LEDS, Adafruit_NeoPixel strip) {
      this->NUM_LEDS = NUM_LEDS;
      this->strip = strip;
    }

    //Disco effect
    void disco() {
      while ( 1 ) {
        Serial.println("DISCOOO");
        for (int i = 0; i < 7; i++) {
          disco_setColor();
          for ( int actLed = 0; actLed < NUM_LEDS; actLed ++) {
            //Serial.println(actLed);
            strip.setPixelColor(actLed, strip.Color(red, green, blue));
            strip.show();
          }
          RdataE = Serial.read();
          if ( RdataE == '0' ) {
            strip.clear();
            return;
          }
          delay(100);
        }
      }
    }
    void disco_setColor() {
      if (actPos >= 7) {
        actPos = 0;
      }
      Serial.println(actPos);
      red = 255 * colorArray[actPos][0];
      green = 255 * colorArray[actPos][1];
      blue = 255 * colorArray[actPos][2];
      actPos ++;
      return ;
    }

    //Christmas effect
    void fullRGW() {
      if (exitStatus == 1) return ;
      for (int actColor = 0; actColor < 3; actColor ++) {
        if (actColor == 0) {
          red = 255; green = 10; blue = 10;
        }
        else if (actColor == 1) {
          red = 10; green = 255; blue = 10;
        }
        else {
          red = 255; green = 190; blue = 190;
        }
        for ( int actLed = 0; actLed < NUM_LEDS; actLed++) {
          strip.setPixelColor(actLed, strip.Color(red, green, blue));
          strip.show();
        }
        delay(500);
      }
      RdataE = Serial.read();
      if ( RdataE == '0' ) {
        strip.clear();
        exitStatus = 1;
        return;
      }
      return;
    }

    void breathing() {
      if (exitStatus == 1) return ;
      for (int bright = 0; bright <= 200; bright += 5) {
        for ( int actLed = 0; actLed < NUM_LEDS; actLed++) {
          strip.setPixelColor(actLed, strip.Color(200, 10, 10));
          strip.setBrightness(bright);
          strip.show();
        }
      }
      for (int bright = 200; bright >= 0; bright -= 5) {
        for ( int actLed = 0; actLed < NUM_LEDS; actLed++) {
          strip.setPixelColor(actLed, strip.Color(200, 10, 10));
          strip.setBrightness(bright);
          strip.show();
        }
      }
      for (int bright = 0; bright <= 200; bright += 5) {
        for ( int actLed = 0; actLed < NUM_LEDS; actLed++) {
          strip.setPixelColor(actLed, strip.Color(10, 255, 10));
          strip.setBrightness(bright);
          strip.show();
        }
      }
      for (int bright = 200; bright >= 0; bright -= 5) {
        for ( int actLed = 0; actLed < NUM_LEDS; actLed++) {
          strip.setPixelColor(actLed, strip.Color(10, 255, 10));
          strip.setBrightness(bright);
          strip.show();
        }
      }
      for (int bright = 0; bright <= 200; bright += 5) {
        for ( int actLed = 0; actLed < NUM_LEDS; actLed++) {
          strip.setPixelColor(actLed, strip.Color(255, 190, 190));
          strip.setBrightness(bright);
          strip.show();
        }
      }
      for (int bright = 200; bright >= 0; bright -= 5) {
        for ( int actLed = 0; actLed < NUM_LEDS; actLed++) {
          strip.setPixelColor(actLed, strip.Color(255, 190, 190));
          strip.setBrightness(bright);
          strip.show();
        }
      }
      strip.setBrightness(100);
      RdataE = Serial.read();
      if ( RdataE == '0' ) {
        strip.clear();
        exitStatus = 1;
        return;
      }
      return ;
    }

    void brightnessRGW() {
      if (exitStatus == 1) return ;
      for (int bright = 200; bright >= 0; bright -= 5) {
        for ( int actLed = 0; actLed < NUM_LEDS; actLed++) {
          int actLedMod = actLed % 3;
          if ( actLedMod == 0) {
            strip.setPixelColor(actLed, strip.Color(255, 0, 0));
            strip.setBrightness(bright);
            strip.show();
          }
          else if (actLedMod == 1) {
            strip.setPixelColor(actLed, strip.Color(0, 255, 0));
            strip.setBrightness(bright);
            strip.show();
          }
          else if (actLedMod == 2) {
            strip.setPixelColor(actLed, strip.Color(255, 190, 190));
            strip.setBrightness(bright);
            strip.show();
          }
        }
      }
      for (int bright = 0; bright <= 200; bright += 5) {
        for ( int actLed = 0; actLed < NUM_LEDS; actLed++) {
          int actLedMod = actLed % 3;
          if ( actLedMod == 0) {
            strip.setPixelColor(actLed, strip.Color(255, 0, 0));
            strip.setBrightness(bright);
            strip.show();
          }
          else if (actLedMod == 1) {
            strip.setPixelColor(actLed, strip.Color(0, 255, 0));
            strip.setBrightness(bright);
            strip.show();
          }
          else if (actLedMod == 2) {
            strip.setPixelColor(actLed, strip.Color(255, 190, 190));
            strip.setBrightness(bright);
            strip.show();
          }
        }
      }
      if (exitStatus == 1) return ;
      RdataE = Serial.read();
      if ( RdataE == '0' ) {
        strip.clear();
        exitStatus = 1;
        return;
      }
      return ;
    }

    void whiteRedGreen() {
      if (exitStatus == 1) return ;
      int times = 3;
      while (times --) {
        for (int actLed = 0; actLed < NUM_LEDS; actLed++) {
          int actLedMod = actLed % 3;
          if ( actLedMod == 0 && times == 2) {
            strip.setPixelColor(actLed, strip.Color(255, 0, 0));
            strip.show();
          }
          else if (actLedMod == 0 && times == 1) {
            strip.setPixelColor(actLed, strip.Color(0, 255, 0));
            strip.show();
          }
          else if (actLedMod == 0 && times == 0) {
            strip.setPixelColor(actLed, strip.Color(255, 190, 190));
            strip.show();
          }
          else if ( actLedMod == 1 && times == 2) {
            strip.setPixelColor(actLed, strip.Color(0, 255, 0));
            strip.show();
          }
          else if (actLedMod == 1 && times == 1) {
            strip.setPixelColor(actLed, strip.Color(255, 190, 190));
            strip.show();
          }
          else if (actLedMod == 1 && times == 0) {
            strip.setPixelColor(actLed, strip.Color(255, 0, 0));
            strip.show();
          }
          else if ( actLedMod == 2 && times == 2) {
            strip.setPixelColor(actLed, strip.Color(255, 190, 190));
            strip.show();
          }
          else if (actLedMod == 2 && times == 1) {
            strip.setPixelColor(actLed, strip.Color(255, 0, 0));
            strip.show();
          }
          else if (actLedMod == 2 && times == 0) {
            strip.setPixelColor(actLed, strip.Color(0, 255, 0));
            strip.show();
          }
        }
        delay(100);
        RdataE = Serial.read();
        if ( RdataE == '0' ) {
          strip.clear();
          exitStatus = 1;
          return;
        }
      }
      return ;
    }
    void christmas() {
      exitStatus = 0;
      while (1) {
        Serial.println("XMASSS");
        //Serial.println(NUM_LEDS);
        for (int effectNr = 0; effectNr < 20; effectNr ++) whiteRedGreen();
        for (int effectNr = 0; effectNr < 2; effectNr ++) brightnessRGW();
        for (int effectNr = 0; effectNr < 2; effectNr ++) breathing();
        for (int effectNr = 0; effectNr < 10; effectNr ++) fullRGW();
        RdataE = Serial.read();
        if ( RdataE == '0' || exitStatus == 1) {
          strip.clear();
          return;
        }
      }
    }

    //Raindrop effect
    void raindrop() {
      while (1) {
        Serial.println("RAINDROP");
        int temp_NUM_LED = NUM_LEDS;
        int stayColor = 5;
        while ( temp_NUM_LED > 0) {
          for ( int actLed = 0; actLed < temp_NUM_LED; actLed++) {
            //a csepp folyamata
            strip.setPixelColor(actLed, strip.Color(0, 0, 5));
            strip.show();
            RdataE = Serial.read();
            if ( RdataE == '0') {
              strip.clear();
              return;
            }
            delay(150);
            if ( actLed < temp_NUM_LED - 1) {
              strip.setPixelColor(actLed, strip.Color(0, 0, 0));
              strip.show();
            }
            else {
              strip.setPixelColor(actLed, strip.Color(0, 0, 0));
              strip.show();
              delay(100);
              strip.setPixelColor(actLed - 1, strip.Color(0, 0, stayColor));
              strip.show();
              delay(100);
              strip.setPixelColor(actLed - 2, strip.Color(0, 0, stayColor));
              strip.show();
              delay(100);
              strip.setPixelColor(actLed - 1, strip.Color(0, 0, 0));
              strip.show();
              delay(100);
              strip.setPixelColor(actLed - 2, strip.Color(0, 0, 0));
              strip.show();
              delay(100);
              strip.setPixelColor(actLed - 1, strip.Color(0, 0, stayColor));
              strip.show();
              delay(100);
              strip.setPixelColor(actLed - 1, strip.Color(0, 0, 0));
              strip.show();
              delay(100);
              strip.setPixelColor(actLed, strip.Color(0, 0, stayColor));
              strip.show();
            }
            int temp = NUM_LEDS - 1;
            //A lent maradt cseppek intenzitata no a melyseggel
            if (actLed >= temp_NUM_LED - 1) {
              while (temp >= actLed) {
                strip.setPixelColor(temp, strip.Color(0, 0, stayColor));
                strip.show();
                temp--;
              }
              stayColor += 7;
            }
          }
          temp_NUM_LED--;
        }
        //Minden kialszik
        strip.clear();
      }
    }

    //Fireplace effect
    void fireplace() {
      exitStatus = 0;
      while (1) {
        Serial.println("FIREEEEE");
        green = 10;
        for ( int effectNr = 0; effectNr < 20;  effectNr ++) randLed();
        for ( int effectNr = 0; effectNr < 20;  effectNr ++) cyclic();
        RdataE = Serial.read();
        if ( RdataE == '0' || exitStatus == 1) {
          return;
        }
      }
    }

    void randLed() {
      if  (exitStatus == 1) return;
      for ( int actPos = 0; actPos < strip.numPixels(); actPos ++)
      {
        strip.setPixelColor(random(0, strip.numPixels()), strip.Color(255, random(0, 40), 0));
        strip.show();
      }
      RdataE = Serial.read();
      if ( RdataE == '0') {
        strip.clear();
        exitStatus = 1;
        return;
      }
      delay(500);
      return ;
    }

    void cyclic() {
      if  (exitStatus == 1) return;
      for (int actLed = 0; actLed < NUM_LEDS; actLed ++) {
        if ( green <= 50 && green > 0)
        {
          green += direction * 10;
        }
        else
        {
          direction *= -1;
          green += direction * 10;
        }
        strip.setPixelColor(actLed, strip.Color(240, green, 0));
        strip.show();
        delay(50);
      }
      RdataE = Serial.read();
      if ( RdataE == '0') {
        strip.clear();
        exitStatus = 1;
        return;
      }
      return ;
    }

    //Romantic effect
    void romantic() {
      while (1) {
        Serial.println("ROMANTIC");
        red = 255; green = 30; blue = 2;
        if (blue <= 50 && blue >= 0) {
          blue += direction * 5;
        }
        else {
          direction *=  -1;
          blue += direction * 5;
        }
        for (int bright = 100; bright >= 0; bright -= 5) {
          for ( int actLed = 0; actLed < NUM_LEDS; actLed++) {
            strip.setPixelColor(actLed, strip.Color(red, 0, blue));
            strip.setBrightness(bright);
            strip.show();
            RdataE = Serial.read();
            if ( RdataE == '0') {
              strip.setBrightness(255);
              strip.clear();
              return;
            }
          }
          delay(100);
        }
        for (int bright = 0; bright <= 100; bright += 5) {
          for ( int actLed = 0; actLed < NUM_LEDS; actLed++) {
            strip.setPixelColor(actLed, strip.Color(red, 0, blue));
            strip.setBrightness(bright);
            strip.show();
            RdataE = Serial.read();
            if ( RdataE == '0') {
              strip.setBrightness(255);
              strip.clear();
              return;
            }
          }
          delay(100);
        }
      }
    }

    //Rainbow effect
    void rainbow() {
      while (1) {
        Serial.println("RAINBOW");
        //Cyclenum-szor fut végig
        // 65536 szin talalhato a színskálán
        for (int firstLedHue = 0; firstLedHue < 65536; firstLedHue += 256) {
          for (int actLed = 0; actLed < strip.numPixels(); actLed++) {
            int ledHue = firstLedHue + (actLed * 65536 / strip.numPixels());
            strip.setPixelColor(actLed, strip.gamma32(strip.ColorHSV(ledHue)));
          }
          strip.show();
          delay(10);
          RdataE = Serial.read();
          if ( RdataE == '0') {
            strip.clear();
            return;
          }
        }
      }
    }

    //Hourglass effect
    void hourglass() {
      while (1) {
        Serial.println("HOURGLASS");
        int temp_NUM_LED = NUM_LEDS;
        int stayColor = 252;
        while ( temp_NUM_LED > 0) {
          for ( int actLed = 0; actLed < temp_NUM_LED; actLed++) {
            //a homokszem folyamata
            strip.setPixelColor(actLed, strip.Color(252, 252, 3));
            strip.show();
            RdataE = Serial.read();
            if ( RdataE == '0') {
              strip.clear();
              return;
            }
            delay(150);
            if ( actLed != temp_NUM_LED - 1) {
              strip.setPixelColor(actLed, strip.Color(0, 0, 0));
              strip.show();
            }
            int temp = NUM_LEDS - 1;
            //A lent maradt cseppek intenzitata no a melyseggel
            if (actLed >= temp_NUM_LED - 1) {
              while (temp >= actLed) {
                strip.setPixelColor(temp, strip.Color(252,  stayColor, 3));
                strip.show();
                temp--;
              }
              stayColor -= 252 / NUM_LEDS;
            }
          }
          temp_NUM_LED--;
        }
        //Minden kialszik
        strip.clear();
      }
    }

    //Smooth white effect
    void smoothwhite() {
      while (1) {
        Serial.println("SMOOTH_WHITE");
        for ( int actBrightness = 0; actBrightness < 256; actBrightness += 2) {
          for ( int actLed = 0; actLed < NUM_LEDS; actLed++)
          {
            strip.setPixelColor(actLed, strip.Color(255, 255, 255));
            strip.setBrightness(actBrightness);
            strip.show();
          }
          RdataE = Serial.read();
          if ( RdataE == '0') {
            strip.clear();
            strip.setBrightness(255);
            return;
          }
        }
        for ( int actBrightness = 255; actBrightness >= 0; actBrightness -= 2) {
          for ( int actLed = 0; actLed < NUM_LEDS; actLed++) {
            strip.setPixelColor(actLed, strip.Color(255, 255, 255));
            strip.setBrightness(actBrightness);
            strip.show();
          }
          RdataE = Serial.read();
          if ( RdataE == '0') {
            strip.setBrightness(255);
            return;
          }
        }
      }
    }

    //Smooth random fade
    void smooth_randomfade() {
      while (1) {
        Serial.println("SMOOTH_RANDOM");
        setColor();
        for ( int actBrightness = 0; actBrightness <= 255; actBrightness += 2) {
          for ( int actLed = 0; actLed < NUM_LEDS; actLed++) {
            strip.setPixelColor(actLed, strip.Color(red, green, blue));
            strip.setBrightness(actBrightness);
            strip.show();
          }
          RdataE = Serial.read();
          if ( RdataE == '0') {
            strip.clear();
            strip.setBrightness(255);
            return;
          }
        }
        for ( int actBrightness = 255; actBrightness >= 0; actBrightness -= 2) {
          for ( int actLed = 0; actLed < NUM_LEDS; actLed++) {
            strip.setPixelColor(actLed, strip.Color(red, green, blue));
            strip.setBrightness(actBrightness);
            strip.show();
          }
          RdataE = Serial.read();
          if ( RdataE == '0') {
            strip.setBrightness(255);
            return;
          }
        }
      }
    }
    //random szinvalasztas
    void setColor() {
      red = random(0, 255);
      green = random(0, 150);
      blue = random(150, 255);
      return ;
    }

    //Random cycle
    void colorCycle() {
      while (1) {
        Serial.println("CYCLE");
        setColor();
        for (int actled = 0; actled < NUM_LEDS; actled++) {
          strip.setPixelColor(actled, strip.Color(red, green, blue));
          strip.show();
          RdataE = Serial.read();
          if ( RdataE == '0') {
            strip.clear();
            return;
          }
          delay(50);
        }
      }
    }

    void myBRGB() {
      while ( 1 ) {
        red = 0; green = 0; blue = 0;
        int brightness = 0;
        Serial.print("red: ");
        while (red == 0) {
          red = Serial.parseInt();
        }
        Serial.println(red);
        if ( red < 0) {
          return;
        }
        Serial.print("green: ");
        while (green == 0) {
          green = Serial.parseInt();
        }
        Serial.println(green);
        Serial.print("blue: ");
        while (blue == 0) {
          blue = Serial.parseInt();
        }
        Serial.println(blue);
        Serial.print("brightness: ");
        while (brightness == 0) {
          brightness = Serial.parseInt();
        }
        for (int actLed = 0; actLed < NUM_LEDS; actLed ++) {
          strip.setPixelColor(actLed, strip.Color(red, green, blue));
          strip.setBrightness(brightness);
          strip.show();
        }
        Serial.println(brightness);
      }
    }

};
