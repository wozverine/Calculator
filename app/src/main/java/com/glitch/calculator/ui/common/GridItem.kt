package com.glitch.calculator.ui.common

import com.glitch.calculator.R

data class GridItem(
	val lightImageRes: Int,
	val darkImageRes: Int,
	val number: Int = 0,
)

val gridItems = listOf(
	GridItem(R.drawable.light_9, R.drawable.dark_9, number = 9),
	GridItem(R.drawable.light_8, R.drawable.dark_8, number = 8),
	GridItem(R.drawable.light_7, R.drawable.dark_7, number = 7),
	GridItem(R.drawable.light_6, R.drawable.dark_6, number = 6),
	GridItem(R.drawable.light_5, R.drawable.dark_5, number = 5),
	GridItem(R.drawable.light_4, R.drawable.dark_4, number = 4),
	GridItem(R.drawable.light_3, R.drawable.dark_3, number = 3),
	GridItem(R.drawable.light_2, R.drawable.dark_2, number = 2),
	GridItem(R.drawable.light_1, R.drawable.dark_1, number = 1),
	GridItem(R.drawable.light_0, R.drawable.dark_0),
	GridItem(R.drawable.light_point, R.drawable.dark_point)
)

val twoByFourGridItems = listOf(
	GridItem(R.drawable.light_e, R.drawable.dark_e),
	GridItem(R.drawable.light_u, R.drawable.dark_u),
	GridItem(R.drawable.light_sin, R.drawable.dark_sin),
	GridItem(R.drawable.light_deg, R.drawable.dark_deg),
	GridItem(R.drawable.light_ac, R.drawable.dark_ac, number = -1),
	GridItem(R.drawable.light_del, R.drawable.dark_del, number = -2),
	GridItem(R.drawable.light_div, R.drawable.dark_div),
	GridItem(R.drawable.light_star, R.drawable.dark_star)
)

