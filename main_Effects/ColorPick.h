// - A myBRBG effekt egy szin r, g, b komponenseinek megadasat es a brightness beallitasat
//   varja. Az effekt akkor fejezodik be, ha az r komponensnek negaiv szamot adunk


#include <Adafruit_NeoPixel.h>

class ColorPick{
  private:
    int NUM_LEDS;
    Adafruit_NeoPixel strip;
    int red, green, blue, brightness;

  public:
    ColorPick(int NUM_LEDS, Adafruit_NeoPixel strip) {
      this->NUM_LEDS = NUM_LEDS;
      this->strip = strip;
    }

     void myBRGB() {
      while ( 1 ) {
        long int allInOne = 0;
        red = 0; green = 0; blue = 0;
        brightness = 0;
        while (brightness == 0) {
          allInOne = BTserial.parseInt();
          if ( allInOne != 0 ) {
            brightness = BTserial.parseInt();
          }
        }
        Serial.println(allInOne);
         if ( allInOne < 0 ) {
          return;
        }
        blue = allInOne % 1000;
        allInOne /= 1000;
        green = allInOne % 1000;
        allInOne /= 1000;
        red = allInOne;
        Serial.println(red); //BTserial.print(red); BTserial.print(";");
        Serial.println(green);
        Serial.println(blue);
        Serial.println(brightness);
        for (int actLed = 0; actLed < NUM_LEDS; actLed ++) {
          strip.setPixelColor(actLed, strip.Color(red, green, blue));
          strip.setBrightness(brightness);
          strip.show();
        }
      }
    }
};

  
