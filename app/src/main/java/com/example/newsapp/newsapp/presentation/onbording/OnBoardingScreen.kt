package com.example.newsapp.newsapp.presentation.onbording

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp.newsapp.presentation.common.NewsButton
import com.example.newsapp.newsapp.presentation.onbording.components.Country
import com.example.newsapp.newsapp.presentation.onbording.components.CountyCard

@Composable
fun OnBoardingScreen(
    countries: List<Country>,
    viewModel: OnBoardingViewModel
) {
    var selectedCountryIndex by remember { mutableIntStateOf(-1) }
    val selectedCountry = countries.getOrNull(selectedCountryIndex)

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Row {
            Text(
                modifier = Modifier.padding(start = 15.dp, end = 10.dp),
                text = "Select your Country",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
            NewsButton(text = "Continue", onClick = {
                if (selectedCountry != null) {
                    viewModel.saveSelectedCountryCode(selectedCountry.code)
                    viewModel.saveAppEntry()
                } else {
                    Log.d("MyTag", "No country selected")
                }
            })
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            items(countries.size) { index ->
                CountyCard(
                    country = countries[index],
                    isSelected = index == selectedCountryIndex,
                    onClick = {
                        if (selectedCountryIndex != index) {
                            selectedCountryIndex = index
                        }
                    },
                )
            }
        }
    }
}
