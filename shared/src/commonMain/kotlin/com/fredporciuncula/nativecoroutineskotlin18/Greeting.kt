package com.fredporciuncula.nativecoroutineskotlin18

class Greeting {
  private val platform: Platform = getPlatform()

  fun greet(): String {
    return "Hello, ${platform.name}!"
  }
}
