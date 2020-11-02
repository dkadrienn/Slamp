#include "effects.h"
#include "Arduino.h"
#include "Game.h"

#define PIN 6
#define NUM_LEDS 30

Adafruit_NeoPixel strip(NUM_LEDS, PIN, NEO_GRB);

Effects effects(NUM_LEDS, strip);
Game game(NUM_LEDS, strip);

String Rdata, RdataSave;

void setup() {
  strip.clear();
  Serial.begin(115200);
  strip.begin();
  //pinMode(PIN, OUTPUT);
}

void loop() {
  while ( Rdata = Serial.readString()) {
    if (Rdata == "" ) {
      Rdata = RdataSave;
    }
    if  (Rdata == "disco\n" ) {
      effects.disco();
      RdataSave = Rdata;
    }
    else if (Rdata == "xmas\n") {
      effects.christmas();
      RdataSave = Rdata;
    }
    else if (Rdata == "raindrop\n") {
      effects.raindrop();
      RdataSave = Rdata;
    }
    else if (Rdata == "fireplace\n") {
      effects.fireplace();
      RdataSave = Rdata;
    }
    else if (Rdata == "romantic\n") {
      effects.romantic();
      RdataSave = Rdata;
    }
    else if (Rdata == "rainbow\n") {
      effects.rainbow();
      RdataSave = Rdata;
    }
    else if (Rdata == "hourglass\n") {
      effects.hourglass();
      RdataSave = Rdata;
    }
    else if (Rdata == "swhite\n") {
      effects.smoothwhite();
      RdataSave = Rdata;
    }
    else if (Rdata == "srandom\n") {
      effects.smooth_randomfade();
      RdataSave = Rdata;
    }
    else if (Rdata == "cycle\n") {
      effects.colorCycle();
      RdataSave = Rdata;
    }
    else if ( Rdata == "dice\n" ) {
      game.dice();
      RdataSave = "swhite\n";
    }
    else if ( Rdata == "timer\n" ) {
      game.timer();
      RdataSave = "swhite\n";
    }
    else if ( Rdata == "pause\n" ) {
      Serial.println("PAUSE");
      //strip.clear();
      RdataSave = Rdata;
    }
    delay(1000);
  }
}
