package com.example.mealsapp.mealDetails.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.example.mealsapp.R

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Tags(str: String) {
    if (str != "null" && str != "") {
        val delimiter = ","
        val tags = str.split(delimiter)
        FlowRow {

            tags.forEach {
                Card(
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.card_tag_padding))
                        .clip(shape = MaterialTheme.shapes.small),
                    border = BorderStroke(1.dp, Color.Gray),
                    elevation = CardDefaults.cardElevation(5.dp)
                ) {
                    Text(
                        text = it, modifier = Modifier
                            .padding(dimensionResource(id = R.dimen.text_tag_padding)),
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }
}