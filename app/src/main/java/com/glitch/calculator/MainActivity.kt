package com.glitch.calculator

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.glitch.calculator.ui.common.gridItems
import com.glitch.calculator.ui.common.twoByFourGridItems
import com.glitch.calculator.ui.theme.CalculatorTheme
import com.glitch.calculator.ui.theme.Purple80
import com.glitch.calculator.ui.theme.PurpleGrey40

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			CalculatorTheme {
				Calculate()
			}
		}
	}
}

fun sumFromString(input: String): Int {
	var result = 0
	var currentNumber = ""
	var currentSign = 1

	for (char in input) {
		when (char) {
			'+' -> {
				result += currentSign * (currentNumber.toIntOrNull() ?: 0)
				currentNumber = ""
				currentSign = 1
			}

			'-' -> {
				result += currentSign * (currentNumber.toIntOrNull() ?: 0)
				currentNumber = ""
				currentSign = -1
			}

			else -> {
				currentNumber += char
			}
		}
	}

	result += currentSign * (currentNumber.toIntOrNull() ?: 0)
	return result
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Calculate() {
	val configuration = LocalConfiguration.current

	val screenWidth = configuration.screenWidthDp
	val screenHeight = configuration.screenHeightDp

	val calculate = remember {
		mutableStateOf("")
	}

	Scaffold(topBar = {
		CenterAlignedTopAppBar(
			title = { Text(text = "Calculator") },
			colors = TopAppBarDefaults.topAppBarColors(
				containerColor = Purple80, titleContentColor = PurpleGrey40
			)
		)
	}) { paddingValues ->

		ConstraintLayout(
			modifier = Modifier
				.fillMaxSize()
				.padding(paddingValues)
		) {
			val (textbox, twobyfour, threebythree, side) = createRefs()

			Box(
				modifier = Modifier
					.height((screenHeight / 5).dp)
					.fillMaxWidth()
					.constrainAs(textbox) {
						top.linkTo(parent.top)
						start.linkTo(parent.start)
						end.linkTo(parent.end)
						bottom.linkTo(twobyfour.top)
					},
				contentAlignment = Alignment.Center
			) {
				Text(
					text = calculate.value.toString(),
					textAlign = TextAlign.Center,
					fontWeight = FontWeight.Bold,
					fontSize = (screenWidth / 10).sp,
				)
			}

			LazyVerticalGrid(
				columns = GridCells.Fixed(4),
				modifier = Modifier
					.constrainAs(twobyfour) {
						top.linkTo(textbox.bottom)
						start.linkTo(parent.start)
						end.linkTo(parent.end)
						bottom.linkTo(threebythree.top)
					}
			) {
				items(twoByFourGridItems.size) { index ->
					val item = twoByFourGridItems[index]
					Box(
						modifier = Modifier
							.fillMaxSize()
							.padding(4.dp)
					) {
						val imageRes =
							if (isSystemInDarkTheme()) item.darkImageRes else item.lightImageRes

						Image(
							painter = painterResource(id = imageRes),
							contentDescription = null,
							modifier = Modifier
								.fillMaxSize()
								.padding(4.dp)
								.clickable(onClick = {
									if (item.number == -1) {
										calculate.value = ""
									}
									if (item.number == -2) {
										if(calculate.value.isNotEmpty())
											calculate.value = calculate.value.dropLast(1)
									}
								}),
							contentScale = ContentScale.Crop
						)
					}
				}
			}

			LazyVerticalGrid(
				columns = GridCells.Fixed(3),
				modifier = Modifier
					.width((screenWidth / 4 * 3).dp)
					.constrainAs(threebythree) {
						top.linkTo(twobyfour.bottom)
						start.linkTo(parent.start)
						bottom.linkTo(parent.bottom)
						end.linkTo(side.start)
					}
					.padding(start = 4.dp)
			) {
				items(gridItems.size) { index ->
					val item = gridItems[index]
					val imageRes =
						if (isSystemInDarkTheme()) item.darkImageRes else item.lightImageRes

					Box(
						modifier = Modifier
							.clickable(onClick = {
								calculate.value.toString() + item.number.toString()
							})
							.padding(2.dp)
							.width((screenWidth / 3).dp)
							.height((screenWidth / 4).dp)
					) {
						Image(
							painter = painterResource(id = imageRes),
							contentDescription = null,
							modifier = Modifier
								.fillMaxSize()
								.padding(4.dp)
								.clickable(onClick = {
									if (calculate.value == "0") {
										calculate.value = item.number.toString()
									} else {
										calculate.value =
											(calculate.value.toString() + (item.number.toString()))
									}
								}),
							contentScale = ContentScale.Crop
						)
					}
				}
			}


			Column(
				modifier = Modifier
					.padding(4.dp)
					.width((screenWidth / 4).dp)
					.constrainAs(side) {
						top.linkTo(threebythree.top)
						start.linkTo(threebythree.end)
						end.linkTo(parent.end)
						bottom.linkTo(parent.bottom)
						height = Dimension.fillToConstraints
					},
				verticalArrangement = Arrangement.spacedBy(5.dp),
				horizontalAlignment = Alignment.CenterHorizontally
			) {
				Box(
					modifier = Modifier
						.clip(RoundedCornerShape(4.dp))
						.weight(1f)
						.padding(end = 4.dp)
						.clickable(onClick = {

						}),
					contentAlignment = Alignment.Center
				) {
					Image(
						painter = painterResource(id = R.drawable.light_minus),
						contentScale = ContentScale.FillBounds,
						contentDescription = null,
						modifier = Modifier
							.fillMaxSize()
							.clickable(onClick = {
								if (calculate.value.lastOrNull() != '+' && calculate.value.lastOrNull() != '-') {
									calculate.value = (calculate.value.toString() + "-")
								}
								if(calculate.value.lastOrNull() == '+'){
									calculate.value = calculate.value.dropLast(1) + "-"
								}
							})
					)
				}
				Box(
					modifier = Modifier
						.clip(RoundedCornerShape(4.dp))
						.padding(end = 4.dp)
						.weight(2f),

					contentAlignment = Alignment.Center
				) {
					Image(
						painter = painterResource(id = R.drawable.light_plus),
						contentScale = ContentScale.FillBounds,
						contentDescription = null,
						modifier = Modifier
							.fillMaxSize()
							.clickable(onClick = {
								if (calculate.value.isNotEmpty() && calculate.value.lastOrNull() != '+' && calculate.value.lastOrNull() != '-') {
									calculate.value += "+"
								}

								if(calculate.value.lastOrNull() == '-' && calculate.value != "-"){
									calculate.value = calculate.value.dropLast(1) + "+"
								}

								if(calculate.value.lastOrNull() != '-'){
									calculate.value = calculate.value.dropLast(1)
									calculate.value = (calculate.value.toString() + "+")
								}
							})
					)
				}
				Box(
					modifier = Modifier
						.clip(RoundedCornerShape(4.dp))
						.weight(2f)
						.padding(end = 4.dp)
						.clickable(onClick = {
							calculate.value = sumFromString(calculate.value.toString()).toString()
						}),
					contentAlignment = Alignment.Center
				) {
					Image(
						painter = painterResource(id = R.drawable.light_equal),
						contentScale = ContentScale.FillBounds,
						contentDescription = null,
						modifier = Modifier
							.fillMaxSize()
					)
				}
			}
		}
	}
}

@Preview(
	showBackground = true,
	uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun GreetingPreview() {
	CalculatorTheme {
		Calculate()
	}
}