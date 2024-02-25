package com.example.mealsapp.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import coil.compose.AsyncImage
import com.example.domain.entity.Category
import com.example.mealsapp.R


@Composable
fun HomeListItem(
    category: Category,
    onItemClick: (Category) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            // .padding(vertical = dimensionResource(id = R.dimen.card_padding_vertical))
            .clickable {
                onItemClick(category)
            }, shape = MaterialTheme.shapes.medium
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            AsyncImage(
                model = category.strCategoryThumb,
                contentDescription = null, modifier = Modifier
                    .size(dimensionResource(id = R.dimen.home_list_image_size))
                // .clip(shape = MaterialTheme.shapes.medium)
            )
            Column {
                Text(
                    text = category.strCategory,
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.text_padding)),
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = category.strCategoryDescription,
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.text_padding)),
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2
                )

            }
        }

    }
}
