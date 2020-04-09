package views.stylesheets

import javafx.scene.paint.Color
import tornadofx.c

/* UI COLOURS */
enum class SignusColour(val value: Color) {
  PRIMARY_DARKER(NordSwatch.POLAR_NIGHT0.color),
  PRIMARY(NordSwatch.POLAR_NIGHT0_5.color),
  SECONDARY(NordSwatch.FROST0.color),
  BACKGROUND(NordSwatch.POLAR_NIGHT1.color),

  /* TEXT */
  TEXT_ON_BG(NordSwatch.SNOW_STORM0.color),
  TEXT_ON_SECONDARY(PRIMARY.value),

  INPUT(NordSwatch.POLAR_NIGHT3.color),
  PROMPT_TEXT(NordSwatch.POLAR_NIGHT5.color)
}

/* Nord Colours */
enum class NordSwatch(val color: Color) {
  /* Polar Night */
  POLAR_NIGHT0(c("#2E3440")),
  POLAR_NIGHT0_5(c("#353C4A")),
  POLAR_NIGHT1(c("#3B4252")),
  POLAR_NIGHT2(c("#434C5E")),
  POLAR_NIGHT3(c("#4C566A")),
  POLAR_NIGHT4(c("#5e6779")),
  POLAR_NIGHT5(c("707888")),
  /* Snow Storm */
  SNOW_STORM0(c("#d8dee9")),
  SNOW_STORM1(c("#e5e9f0")),
  SNOW_STORM2(c("#eceff4")),
  /* Frost */
  FROST0(c("#8fbcbb")),
  FROST1(c("#88c0d0")),
  FROST2(c("#81a1c1")),
  FROST3(c("#5e81ac")),
  /* Aurora */
  AURORA0(c("#BF616A")),
  AURORA1(c("#D08770")),
  AURORA2(c("#EBCB8B")),
  AURORA3(c("#A3BE8C")),
  AURORA4(c("#B48EAD"))
}