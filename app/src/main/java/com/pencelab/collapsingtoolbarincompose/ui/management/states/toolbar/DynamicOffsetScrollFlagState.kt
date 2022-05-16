package com.pencelab.collapsingtoolbarincompose.ui.management.states.toolbar

abstract class DynamicOffsetScrollFlagState(
    heightRange: IntRange,
    scrollValue: Int
) : ScrollFlagState(heightRange, scrollValue) {

    protected abstract var scrollOffset: Float

}