package com.fredporciuncula.nativecoroutineskotlin18

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.StateFlow

class RegularClass {
  val channel = Channel<String>(Channel.CONFLATED)
  val testFlow: Flow<String> = channel.receiveAsFlow()

  val _testStateFlow = MutableStateFlow("initial")
  val testStateFlow: StateFlow<String> = _testStateFlow.asStateFlow()

  suspend fun testSuspend(): String {
    delay(1000)
    return "hallo"
  }
}

abstract class AbstractClass<T>(private val initial: T) {
  val channel = Channel<T>(Channel.CONFLATED)
  val testFlow: Flow<T> = channel.receiveAsFlow()

  val _testStateFlow = MutableStateFlow(initial)
  val testStateFlow: StateFlow<T> = _testStateFlow.asStateFlow()

  suspend fun testSuspend(): T {
    delay(1000)
    return initial
  }
}

class Implementation : AbstractClass<String>(initial = "initial")
