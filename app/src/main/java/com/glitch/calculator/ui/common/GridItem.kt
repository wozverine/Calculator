package com.glitch.calculator.ui.common

import com.glitch.calculator.R

data class GridItem(
	val imageRes: Int,
	val span: Int = 1,
	val number: Int = 0,
)


val gridItems = listOf(
	GridItem(R.drawable.light_9, number = 1),
	GridItem(R.drawable.light_8, number = 8),
	GridItem(R.drawable.light_7, number = 7),
	GridItem(R.drawable.light_6, number = 6),
	GridItem(R.drawable.light_5, number = 5),
	GridItem(R.drawable.light_4, number = 4),
	GridItem(R.drawable.light_3, number = 3),
	GridItem(R.drawable.light_2, number = 2),
	GridItem(R.drawable.light_1, number = 1),
	GridItem(R.drawable.light_0, 2),
	GridItem(R.drawable.light_point)
)

val twoByFourGridItems = listOf(
	GridItem(R.drawable.light_e),
	GridItem(R.drawable.light_u),
	GridItem(R.drawable.light_sin),
	GridItem(R.drawable.light_deg),
	GridItem(R.drawable.light_ac, number = -1),
	GridItem(R.drawable.light_del),
	GridItem(R.drawable.light_div),
	GridItem(R.drawable.light_star)
)
