package com.glitch.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.ui.ExperimentalComposeUiApi
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
import com.glitch.calculator.ui.common.GridItem
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
				Calculate(

				)
			}
		}
	}
}

@Composable
fun GridItem(item: GridItem) {
	Image(
		painter = painterResource(id = item.imageRes),
		contentDescription = null,
		modifier = Modifier
			.clickable(onClick = {
				//calculate.value = (calculate.value.toString() + (item.number.toString()))
			})
			.padding(4.dp),
		//.fillMaxSize(),
		//.aspectRatio(1f),
		contentScale = ContentScale.Crop
	)
}

fun sumFromString(input: String): Int {
	val cleanedInput = if (input.endsWith("+")) input.dropLast(1) else input
	return cleanedInput.split("+")
		.map { it.toIntOrNull() ?: 0 }
		.sum()
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
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

			Text(
				text = calculate.value.toString(),

				modifier = Modifier
					.height((screenHeight / 6).dp)
					.fillMaxWidth()
					.constrainAs(textbox) {
						top.linkTo(parent.top)
						start.linkTo(parent.start)
						end.linkTo(parent.end)
						bottom.linkTo(twobyfour.top)
					},
				textAlign = TextAlign.Center,
				fontWeight = FontWeight.Bold,
				fontSize = (screenWidth / 10).sp,
			)

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
							//.width((screenWidth/4).dp)
							.padding(4.dp)
					) {
						Image(
							painter = painterResource(id = item.imageRes),
							contentDescription = null,
							modifier = Modifier
								.fillMaxSize()
								.padding(4.dp)
								.clickable(onClick = {
									if (item.number == -1) {
										calculate.value = ""
									}

									//Log.v("pressed","pressed")
									/*calculate.value =
										(calculate.value.toString() + (item.number.toString()))*/
								}),

							contentScale = ContentScale.Crop
						)
						//GridItem(item)
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
					.padding(4.dp)
			) {
				items(gridItems.size) { index ->
					val item = gridItems[index]
					Box(
						modifier = Modifier
							.clickable(onClick = {
								calculate.value.toString() + item.number.toString()
							})
							.padding(4.dp)
							.run {
								if (item.span == 2) {
									val height = Modifier
										.fillMaxSize()
										.aspectRatio(2f)
										//.width((screenWidth / 3 * 2).dp)
										.height((screenWidth / 4).dp)
									height
								} else {
									Modifier
										.width((screenWidth / 3).dp)
										.height((screenWidth / 4).dp)
								}
							}
					) {
						Image(
							painter = painterResource(id = item.imageRes),
							contentDescription = null,
							modifier = Modifier
								.fillMaxSize()
								.padding(4.dp)
								.clickable(onClick = {
									//Log.v("pressed","pressed")
									calculate.value =
										(calculate.value.toString() + (item.number.toString()))
								}),

							contentScale = ContentScale.Crop
						)
					}
				}
			}

			/*LazyVerticalGrid(
				modifier = Modifier
					.width((screenWidth / 4 * 3).dp)
					.constrainAs(threebythree) {
						top.linkTo(twobyfour.bottom)
						start.linkTo(parent.start)
						bottom.linkTo(parent.bottom)
						end.linkTo(side.start)
					},
				columns = GridCells.Fixed(3)
			) {
				items(gridItems.size) { index ->
					GridItem(gridItems[index])
				}
			}*/

			Column(
				modifier = Modifier
					.padding(3.dp)
					.width((screenWidth / 4).dp)
					.constrainAs(side) {
						top.linkTo(threebythree.top)
						start.linkTo(threebythree.end)
						end.linkTo(parent.end)
						bottom.linkTo(parent.bottom)
						height = Dimension.fillToConstraints
					},
				verticalArrangement = Arrangement.SpaceBetween,
				horizontalAlignment = Alignment.CenterHorizontally
			) {
				Box(
					modifier = Modifier
						.clip(RoundedCornerShape(4.dp))
						.weight(1f)
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
						//.size((screenWidth / 4).dp)
					)
				}
				Box(
					modifier = Modifier
						.clip(RoundedCornerShape(4.dp))
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
								//Log.v("pressed","pressed")
								calculate.value = (calculate.value.toString() + "+")
							})
						//.size((screenWidth / 4).dp)
					)
				}
				Box(
					modifier = Modifier
						.clip(RoundedCornerShape(4.dp))
						.weight(2f)
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
						//.size((screenWidth / 4).dp)
					)
				}
			}
		}
	}
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
	CalculatorTheme {
		Calculate()
	}
}