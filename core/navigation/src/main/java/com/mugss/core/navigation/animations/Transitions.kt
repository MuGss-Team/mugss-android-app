package com.mugss.core.navigation.animations

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.tween
import androidx.navigation.NavBackStackEntry

fun AnimatedContentTransitionScope<NavBackStackEntry>.defaultSlideEnterTransition(): EnterTransition =
    slideIntoContainer(
        animationSpec = tween(ANIMATION_DURATION, easing = EaseIn),
        towards = AnimatedContentTransitionScope.SlideDirection.Start,
    )

fun AnimatedContentTransitionScope<NavBackStackEntry>.defaultSlideExitTransition(): ExitTransition =
    slideOutOfContainer(
        animationSpec = tween(ANIMATION_DURATION, easing = EaseIn),
        towards = AnimatedContentTransitionScope.SlideDirection.Start,
    )

fun AnimatedContentTransitionScope<NavBackStackEntry>.defaultSlidePopEnterTransition(): EnterTransition =
    slideIntoContainer(
        animationSpec = tween(ANIMATION_DURATION, easing = EaseIn),
        towards = AnimatedContentTransitionScope.SlideDirection.End,
    )

fun AnimatedContentTransitionScope<NavBackStackEntry>.defaultSlidePopExitTransition(): ExitTransition =
    slideOutOfContainer(
        animationSpec = tween(ANIMATION_DURATION, easing = EaseIn),
        towards = AnimatedContentTransitionScope.SlideDirection.End,
    )

private const val ANIMATION_DURATION = 300
