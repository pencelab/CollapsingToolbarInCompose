package com.pencelab.collapsingtoolbarincompose.ui.management.states.toolbar

abstract class FixedScrollFlagState(heightRange: IntRange) : ScrollFlagState(heightRange) {

    final override val offset: Float = 0f

}