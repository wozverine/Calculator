package com.glitch.calculator.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.glitch.calculator.R
import com.glitch.calculator.ui.theme.CalculatorTheme

@Composable
fun CustomButton(
	idImage: Int,
	modifier: Modifier = Modifier
) {
	val configuration = LocalConfiguration.current

	val screenWidth = configuration.screenWidthDp
	val screenHeight = configuration.screenHeightDp

	Box(
		modifier = modifier
			.clip(RoundedCornerShape(4.dp))
			.clickable(onClick = {

			}),
		contentAlignment = Alignment.Center
	) {
		Image(
			painter = painterResource(id = idImage),
			contentScale = ContentScale.FillBounds,
			contentDescription = null,
			modifier = Modifier
				//.fillMaxSize()
				//.size((screenWidth / 4).dp)
		)
	}
}

@Preview(showBackground = true)
@Composable
fun CalculatorPreview() {
	CalculatorTheme {
		CustomButton(R.drawable.light_plus)
	}
}

