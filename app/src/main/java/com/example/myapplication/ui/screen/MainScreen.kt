package com.example.myapplication.ui.screen

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val DeepDark = Color(0xFF050511)
val NeonCyan = Color(0xFF00F0FF)
val NeonPurple = Color(0xFFBC13FE)

@Composable
fun MainScreen(
    onButtonClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepDark)
    ) {
        NebulaGlow()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(horizontal = 24.dp, vertical = 32.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.size(8.dp).background(NeonCyan, CircleShape))
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "SYSTEM_V.4.0 // ONLINE",
                    color = Color.Gray,
                    fontSize = 12.sp,
                    letterSpacing = 2.sp,
                    fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Box(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                contentAlignment = Alignment.Center
            ) {
                RotatingRings()

                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(32.dp)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            GradientText(text = "Explorez\nl'Infini.")

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Accédez à la base de données neuronale des versions Android.",
                color = Color.LightGray.copy(alpha = 0.6f),
                fontSize = 16.sp,
                lineHeight = 24.sp
            )

            Spacer(modifier = Modifier.height(48.dp))

            CyberButton(onClick = onButtonClick)
        }
    }
}

@Composable
fun CyberButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White.copy(alpha = 0.05f))
            .border(1.dp, Color.White.copy(alpha = 0.1f), RoundedCornerShape(16.dp))
            .clickable { onClick() },
        contentAlignment = Alignment.CenterStart
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(100.dp)
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(NeonPurple.copy(alpha = 0.2f), Color.Transparent)
                    )
                )
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "INITIALISER",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                letterSpacing = 1.5.sp
            )

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .border(1.dp, NeonCyan, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null,
                    tint = NeonCyan,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@Composable
fun RotatingRings() {
    val infiniteTransition = rememberInfiniteTransition(label = "rings")

    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(tween(8000, easing = LinearEasing)),
        label = "rotation"
    )

    val pulse by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.1f,
        animationSpec = infiniteRepeatable(tween(2000, easing = FastOutSlowInEasing), RepeatMode.Reverse),
        label = "pulse"
    )

    Canvas(modifier = Modifier.size(280.dp).scale(pulse)) {
        val center = Offset(size.width / 2, size.height / 2)

        rotate(angle, center) {
            drawCircle(
                brush = Brush.sweepGradient(listOf(Color.Transparent, NeonCyan, Color.Transparent)),
                radius = size.width / 2,
                style = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round)
            )
        }

        rotate(-angle * 1.5f, center) {
            drawCircle(
                brush = Brush.sweepGradient(listOf(Color.Transparent, NeonPurple, Color.Transparent)),
                radius = size.width / 3,
                style = Stroke(width = 4.dp.toPx(), cap = StrokeCap.Round)
            )
        }

        drawCircle(
            color = Color.White.copy(alpha = 0.05f),
            radius = size.width / 2.5f,
            style = Stroke(width = 1.dp.toPx())
        )
    }
}

@Composable
fun GradientText(text: String) {
    val gradientBrush = Brush.linearGradient(
        colors = listOf(Color.White, NeonCyan, NeonPurple)
    )

    Text(
        text = text,
        style = TextStyle(
            brush = gradientBrush,
            fontSize = 48.sp,
            fontWeight = FontWeight.ExtraBold,
            letterSpacing = (-1).sp
        )
    )
}

@Composable
fun NebulaGlow() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.radialGradient(
                    colors = listOf(
                        NeonPurple.copy(alpha = 0.15f),
                        DeepDark
                    ),
                    center = Offset(0f, 0f),
                    radius = 1000f
                )
            )
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.radialGradient(
                    colors = listOf(
                        NeonCyan.copy(alpha = 0.1f),
                        Color.Transparent
                    ),
                    center = Offset(1000f, 2000f),
                    radius = 1200f
                )
            )
    )
}