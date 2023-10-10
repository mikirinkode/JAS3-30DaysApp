package com.mikirinkode.thirtydaysapp

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mikirinkode.thirtydaysapp.data.model.DailyActivity
import com.mikirinkode.thirtydaysapp.data.model.DummyData
import com.mikirinkode.thirtydaysapp.ui.theme.PrincessSofiaFamily
import com.mikirinkode.thirtydaysapp.ui.theme.ThirtyDaysAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DailyActivityListScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
            ) {
                Text(
                    text = "30 Days Creative Challenge",
                    fontFamily = PrincessSofiaFamily,
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    modifier = Modifier.fillMaxWidth(),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            DailyActivityList(list = DummyData.getDailyActivityList())
        }
    }
}

@Composable
fun DailyActivityList(
    list: List<DailyActivity>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = PaddingValues(bottom = 16.dp),
        modifier = modifier
    ) {
        itemsIndexed(list) { index: Int, item: DailyActivity ->
            DailyActivityCard(
                dayNumber = index + 1,
                name = item.name,
                desc = item.description,
                imageId = item.illustrationId
            )
        }
    }
}

@Composable
fun DailyActivityCard(
    dayNumber: Int,
    name: String,
    desc: String,
    imageId: Int,
    modifier: Modifier = Modifier
) {
    var isDescVisible by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
            .background(color = MaterialTheme.colorScheme.primaryContainer, shape = MaterialTheme.shapes.medium)
            .clip(MaterialTheme.shapes.medium)
            .clickable(onClick = {
                isDescVisible = !isDescVisible
            })
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Day $dayNumber",
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.primary.copy(
                                0.3f
                            ), shape = MaterialTheme.shapes.small
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = if (isDescVisible) {
                        Icons.Rounded.KeyboardArrowDown
                    } else {
                        Icons.Rounded.KeyboardArrowUp
                    },
                    contentDescription = "Show / Hide Description Icon"
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Image(
                painter = painterResource(id = imageId),
                contentDescription = "Activity Illustration",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.medium)
                    .height(200.dp),
            )
            AnimatedVisibility(visible = isDescVisible) {
                Column() {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(desc, textAlign = TextAlign.Justify)
                }
            }
        }
    }
}

@Preview(name = "light", showBackground = true, showSystemUi = true)
@Preview(
    name = "night",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun DailyActivityListScreenPreview() {
    ThirtyDaysAppTheme {
        Surface() {
            DailyActivityListScreen()
        }
    }
}