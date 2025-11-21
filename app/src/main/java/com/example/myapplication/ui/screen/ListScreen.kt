package com.example.myapplication.ui.screen

import AndroidVersionViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.myapplication.ui.model.ItemUi

private val CyberRed = Color(0xFFFF2A6D)
private val NeonGreen = Color(0xFF00FF41)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondScreen(
    navigateBack: () -> Unit,
) {
    val viewModel: AndroidVersionViewModel = viewModel()
    val listOfResult = viewModel.androidVersionList.collectAsState().value
    Scaffold(
        containerColor = DeepDark,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Versions d'Android",
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold,
                        color = NeonCyan,
                        letterSpacing = 2.sp,
                        fontSize = 14.sp
                    )
                },
                navigationIcon = {
                    Box(
                        modifier = Modifier
                            .padding(start = 12.dp)
                            .size(40.dp)
                            .border(1.dp, NeonCyan.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
                            .background(NeonCyan.copy(alpha = 0.05f), RoundedCornerShape(8.dp))
                            .clickable { navigateBack() },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "BACK",
                            tint = NeonCyan,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Transparent
                ),
                modifier = Modifier.drawBehind {
                    val strokeWidth = 2.dp.toPx()
                    val y = size.height - strokeWidth / 2
                    drawLine(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Color.Transparent,
                                NeonCyan.copy(alpha = 0.8f),
                                Color.Transparent
                            )
                        ),
                        start = Offset(0f, y),
                        end = Offset(size.width, y),
                        strokeWidth = strokeWidth
                    )
                }
            )
        },
    ) { innerPadding ->
        MyScreen(
            listOfResult = listOfResult,
            modifier = Modifier.padding(innerPadding),
            onAddClick = { viewModel.insertAndroidVersion() },
            onDeleteClick = { viewModel.deleteAllAndroidVersion() }
        )
    }
}

@Composable
private fun MyScreen(
    listOfResult: List<ItemUi>,
    modifier: Modifier = Modifier,
    onAddClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(DeepDark)
    ) {
        NebulaGlowList()

        Column(modifier = Modifier.fillMaxSize()) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CyberActionButton(
                    text = "INJECT_DATA",
                    icon = Icons.Default.Add,
                    color = NeonCyan,
                    onClick = onAddClick,
                    modifier = Modifier.weight(1f)
                )

                CyberActionButton(
                    text = "PURGE_ALL",
                    icon = Icons.Default.Delete,
                    color = CyberRed,
                    onClick = onDeleteClick,
                    modifier = Modifier.weight(1f)
                )
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(bottom = 24.dp)
            ) {
                items(listOfResult) { item ->
                    when (item) {
                        is ItemUi.Header -> {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 24.dp, bottom = 8.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(12.dp)
                                        .background(NeonGreen)
                                        .border(1.dp, Color.White, androidx.compose.ui.graphics.RectangleShape)
                                )

                                Spacer(modifier = Modifier.width(12.dp))

                                Text(
                                    text = item.title.uppercase(),
                                    style = MaterialTheme.typography.titleMedium,
                                    color = NeonGreen,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontFamily = FontFamily.Monospace,
                                    letterSpacing = 1.5.sp
                                )

                                Spacer(modifier = Modifier.width(8.dp))

                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(1.dp)
                                        .background(
                                            Brush.horizontalGradient(
                                                colors = listOf(NeonGreen, Color.Transparent)
                                            )
                                        )
                                )
                            }
                        }

                        is ItemUi.Item -> {
                            var showDialog by remember { mutableStateOf(false) }
                            CyberItemCard(item = item, onClick = { showDialog = true })

                            if (showDialog) {
                                CyberDialog(
                                    item = item,
                                    onDismiss = { showDialog = false }
                                )
                            }
                        }

                        is ItemUi.Footer -> {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 24.dp)
                                    .border(1.dp, Color.White.copy(alpha = 0.1f), RoundedCornerShape(8.dp))
                                    .background(Color.Black.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
                                    .padding(12.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "SYSTEM_STATUS: ${item.numberOfElement} UNITS LOADED",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = Color.Gray,
                                    fontFamily = FontFamily.Monospace
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun CyberActionButton(
    text: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    color: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(50.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = color.copy(alpha = 0.1f),
            contentColor = color
        ),
        border = androidx.compose.foundation.BorderStroke(1.dp, color.copy(alpha = 0.5f)),
        elevation = ButtonDefaults.buttonElevation(0.dp)
    ) {
        Icon(imageVector = icon, contentDescription = null, modifier = Modifier.size(18.dp))
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace,
            fontSize = 12.sp
        )
    }
}

@Composable
fun CyberItemCard(item: ItemUi.Item, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White.copy(alpha = 0.03f))
            .border(1.dp, Color.White.copy(alpha = 0.1f), RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .padding(12.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .border(1.dp, NeonCyan.copy(alpha = 0.5f), CircleShape)
                    .padding(2.dp)
            ) {
                val painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(data = item.versionUrl)
                        .crossfade(true)
                        .build()
                )
                Image(
                    painter = painter,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.versionName,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "v.${item.versionNumber}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = NeonCyan,
                    fontFamily = FontFamily.Monospace
                )
                Text(
                    text = "RELEASE: ${item.versionYear}",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.Gray,
                    fontFamily = FontFamily.Monospace
                )
            }

            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = Color.DarkGray
            )
        }
    }
}

@Composable
fun CyberDialog(item: ItemUi.Item, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = DeepDark,
        textContentColor = Color.White,
        titleContentColor = NeonCyan,
        modifier = Modifier.border(1.dp, NeonCyan.copy(alpha = 0.3f), RoundedCornerShape(24.dp)),
        title = {
            Text(
                text = item.versionName.uppercase(),
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )
        },
        text = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .size(140.dp)
                        .border(1.dp, NeonPurple, CircleShape)
                        .padding(4.dp)
                        .clip(CircleShape)
                ) {
                    val bigPainter = rememberAsyncImagePainter(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(data = item.versionUrl)
                            .crossfade(true)
                            .build()
                    )
                    Image(
                        painter = bigPainter,
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                DataRow(label = "VERSION_ID", value = item.versionNumber)
                DataRow(label = "BUILD_YEAR", value = item.versionYear)
                DataRow(label = "SYS_UID", value = item.id.toString())
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("CLOSE_TERMINAL", color = NeonCyan, fontFamily = FontFamily.Monospace)
            }
        }
    )
}

@Composable
fun DataRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, color = Color.Gray, fontSize = 12.sp, fontFamily = FontFamily.Monospace)
        Text(text = value, color = Color.White, fontSize = 12.sp, fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun NebulaGlowList() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.radialGradient(
                    colors = listOf(NeonPurple.copy(alpha = 0.05f), Color.Transparent),
                    center = Offset(0f, 0f),
                    radius = 800f
                )
            )
    )
}