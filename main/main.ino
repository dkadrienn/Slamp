//Az effect egy-egy karakter megadasaval indithatok!
//Az alabbi karakterekkel lehet inditani a kulonbozo effekteket:
// a - Disco effect         h - Smooth White effect
// b - Christmas effect     i - Smooth Random effect
// c - Raindrop effect      j - Cycle effect
// d - Fireplace effect     k - Dice effect
// e - Romantic effect      l - Timer effect
// f - Rainbow effect       m - myBRGB effect
// g - Hourglass effect

#include "Effects.h"
#include "Arduino.h"
#include "Game.h"

#ifdef __AVR__
 #include <avr/power.h> // Required for 16 MHz Adafruit Trinket
#endif


#define PIN 2
#define NUM_LEDS 28

Adafruit_NeoPixel strip(NUM_LEDS, PIN, NEO_GRB + NEO_KHZ800);

char Rdata;

void setup() {
  #if defined(__AVR_ATtiny85__) && (F_CPU == 16000000)
  clock_prescale_set(clock_div_1);
  #endif
  strip.clear();
  Serial.begin(9600);
  strip.begin();
  //pinMode(PIN, OUTPUT);
}

Effects effects(NUM_LEDS, strip);
Game game(NUM_LEDS, strip);

void loop() {
  while ( Serial.available() ) {
    Serial.println("----");
    Rdata = Serial.read();
    if (Rdata == 'a'){ /// a - Disco effect
      strip.clear();
      delay(10);
      effects.disco();
      Serial.println("DISCO--Vissza"); 
    }
    if (Rdata == 'b'){ // b - Christmas effect 
      strip.clear();
      delay(10);
      effects.christmas();
      Serial.println("XMAS--Vissza"); 
    }
    if (Rdata == 'c'){ // c - Raindrop effect
      strip.clear();
      delay(10);
      effects.raindrop();
      Serial.println("RAINDROP--Vissza"); 
    }
    if (Rdata == 'd'){ // d - Fireplace effect
      strip.clear();
      delay(10);
      effects.fireplace();
      Serial.println("FIREPLACE--Vissza"); 
    }
    if (Rdata == 'e'){ // e - Romantic effect
      strip.clear();
      delay(10);
      effects.romantic();
      Serial.println("ROMANTIC--Vissza"); 
    }
    if (Rdata == 'f'){ // f - Rainbow effect
      strip.clear();
      delay(10);
      effects.rainbow();
      Serial.println("Rainbow--Vissza"); 
    }
    if (Rdata == 'g'){ // g - Hourglass effect
      strip.clear();
      delay(10);
      effects.hourglass();
      Serial.println("HOURGLASS--Vissza"); 
    }
    if (Rdata == 'h'){ // h - SmoothWhite effect
      strip.clear();
      delay(10);
      effects.smoothwhite();
      Serial.println("SWHITE--Vissza"); 
    }
    if (Rdata == 'i'){ // i - SmoothRandom effect
      strip.clear();
      delay(10);
      effects.smooth_randomfade();
      Serial.println("SRANDOM--Vissza"); 
    }
    if (Rdata == 'j'){ // j - ColorCycle effect
      strip.clear();
      effects.colorCycle();
      Serial.println("CYCLE--Vissza"); 
    }
    if (Rdata == 'k'){ // k - Dice game effect
      strip.clear();
      delay(10);
      game.dice();
      Serial.println("DICE--Vissza"); 
    }
    if (Rdata == 'l'){ // l - Timer game effect
      strip.clear();
      delay(10);
      game.timer();
      Serial.println("TIMER--Vissza"); 
    }
    if (Rdata == 'm'){ // l - MYBRGB effect
      strip.clear();
      delay(10);
      effects.myBRGB();
      strip.setBrightness(255);
      Serial.println("BRGB--Vissza"); 
    }
  }
}
