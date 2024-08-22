package com.glitch.calculator.ui.common

import com.glitch.calculator.R

data class GridItem(
	val imageRes: Int
)


val gridItems = listOf(
	GridItem(R.drawable.light_9),
	GridItem(R.drawable.light_8),
	GridItem(R.drawable.light_7),
	GridItem(R.drawable.light_6),
	GridItem(R.drawable.light_5),
	GridItem(R.drawable.light_4),
	GridItem(R.drawable.light_3),
	GridItem(R.drawable.light_2),
	GridItem(R.drawable.light_1)
)

val twoByFourGridItems = listOf(
	GridItem(R.drawable.light_e),
	GridItem(R.drawable.light_u),
	GridItem(R.drawable.light_sin),
	GridItem(R.drawable.light_deg),
	GridItem(R.drawable.light_ac),
	GridItem(R.drawable.light_del),
	GridItem(R.drawable.light_div),
	GridItem(R.drawable.light_star)
)
