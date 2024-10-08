package com.ringo.pizzadeleivery.presentation.ui

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.imageLoader
import com.ringo.pizzadeleivery.R
import com.ringo.pizzadeleivery.domain.Pizza
import com.ringo.pizzadeleivery.presentation.ui.theme.subText
import com.ringo.pizzadeleivery.presentation.ui.theme.thirdColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun PizzaList(paddingValues: PaddingValues) {
    val test = listOf(
            Pizza(
                "",
                "Пепперони",
                299,
                "Пикантная пепперони, увеличенная порция моцареллы, томаты, фирменный томатный соус"),
            Pizza(
                "https://img.freepik.com/premium-photo/pizza-isolated-white-background_1066853-1982.jpg?w=1380",
                "Сырная",
                299,
                "Сырная пицца вкусно "
            ),
        )
    var isLoading by remember {
        mutableStateOf(true)
    }
    LaunchedEffect(key1 = true) {
        delay(5000)
        isLoading = false
    }

    LazyColumn(
        modifier = Modifier
           // .fillMaxWidth()
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        items(test){
            Column {
                Spacer(modifier = Modifier.height(10.dp))
               /* ShimmerListItem(
                    isLoading = isLoading,
                    contentAfterLoading = {*/
                PizzaCard(pizza = it)
                  //  },
                    /*modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp))
            }*/
        }
    }
}
/*@Composable
fun ShimmerListItem(
    isLoading: Boolean,
    contentAfterLoading: @Composable () -> Unit,
    modifier: Modifier
) {
    if(isLoading){
        Row(modifier = modifier) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .shimmerEffect()
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
                    .shimmerEffect()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
                    .shimmerEffect()
                )
            }
        }
    }else{
        contentAfterLoading()
    }
}*/
/*@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.shimmerEffect() : Modifier = composed {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    val transition = rememberInfiniteTransition(label = "")
    val startOffsetX by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation =  tween(
                durationMillis = 1000
            )
        ), label = ""
    )

    background(
            brush = Brush.linearGradient(colors = listOf(
                Color(0x8FF8B8B5),
                Color(0x8FF8B8B5),
                Color(0x8FF8B8B5),
                ),
                start = Offset(startOffsetX, 0f),
                end =  Offset(startOffsetX + size.width.toFloat(),size.height.toFloat())
        )
    ).onGloballyPositioned {
        size = it.size
    }
}*/

    }
@Composable
fun PizzaCard(pizza: Pizza) {
    val context = LocalContext.current
    Card(
        onClick = {
            Toast.makeText(context, pizza.name, Toast.LENGTH_SHORT).show()
        },
        shape = RoundedCornerShape(10),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        modifier = Modifier
            .fillMaxWidth()

    ) {
        Row() {
            //Image(painter = painterResource(id = pizza.image), contentDescription = null, modifier = Modifier.size(150.dp))
            SubcomposeAsyncImage(
                model = pizza.image,
                contentDescription = null,
                modifier = Modifier.size(150.dp),
                loading = {
                    CircularProgressIndicator()
                }
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = pizza.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = pizza.description, color = subText, fontWeight = FontWeight.Medium)
                Spacer(modifier = Modifier.height(5.dp))
                Row(
                    modifier = Modifier
                        .background(color = thirdColor, RoundedCornerShape(40))
                        .width(75.dp)
                        .height(20.dp)
                ) {
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "от ${pizza.price} ₽",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}