package com.fredporciuncula.nativecoroutineskotlin18

interface Platform {
  val name: String
}

expect fun getPlatform(): Platform
