package com.curzy.specmd.ui.screen.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.curzy.specmd.R
import com.curzy.specmd.ui.components.PreviewDialog
import com.curzy.specmd.ui.components.SettingsBottomSheet
import com.curzy.specmd.ui.components.ShimmerLoading
import com.curzy.specmd.ui.components.SpecCard
import com.curzy.specmd.ui.theme.BgBase
import com.curzy.specmd.ui.theme.BgOverlay
import com.curzy.specmd.ui.theme.Primary
import com.curzy.specmd.ui.theme.Success
import com.curzy.specmd.ui.theme.TextOnAccent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val sheetState = rememberModalBottomSheetState()

    LaunchedEffect(state.toastMessage) {
        state.toastMessage?.let {
            snackbarHostState.showSnackbar(it)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BgBase)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
        ) {
            // Top App Bar
            TopBar(
                onSettingsClick = { viewModel.onEvent(HomeEvent.ShowExportSheet) }
            )

            // Content
            if (state.isLoading) {
                ShimmerLoading(
                    modifier = Modifier.weight(1f)
                )
            } else {
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    item { Spacer(modifier = Modifier.height(8.dp)) }

                    state.deviceSpec?.let { spec ->
                        items(
                            listOf(
                                spec.deviceIdentity,
                                spec.software,
                                spec.hardware,
                                spec.display,
                                spec.battery
                            )
                        ) { section ->
                            SpecCard(section = section)
                        }
                    }

                    item { Spacer(modifier = Modifier.height(80.dp)) }
                }
            }
        }

        // Bottom Action Button
        AnimatedVisibility(
            visible = !state.isLoading,
            enter = slideInVertically { it } + fadeIn(),
            exit = slideOutVertically { it } + fadeOut(),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        ) {
            ActionButton(
                isCopied = state.isCopied,
                onClick = { viewModel.onEvent(HomeEvent.CopyToClipboard) }
            )
        }

        // Snackbar Host
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 100.dp)
        )

        // Settings Bottom Sheet
        if (state.showSettingsSheet) {
            ModalBottomSheet(
                onDismissRequest = { viewModel.onEvent(HomeEvent.DismissExportSheet) },
                sheetState = sheetState,
                containerColor = BgOverlay
            ) {
                SettingsBottomSheet(
                    donateUrl = state.donateUrl,
                    onCopy = {
                        viewModel.onEvent(HomeEvent.CopyToClipboard)
                        viewModel.onEvent(HomeEvent.DismissExportSheet)
                    },
                    onShare = {
                        viewModel.shareContent()
                        viewModel.onEvent(HomeEvent.DismissExportSheet)
                    },
                    onSave = {
                        viewModel.saveToFile()
                        viewModel.onEvent(HomeEvent.DismissExportSheet)
                    },
                    onPreview = {
                        viewModel.onEvent(HomeEvent.TogglePreview)
                        viewModel.onEvent(HomeEvent.DismissExportSheet)
                    }
                )
            }
        }

        // Preview Dialog
        if (state.showPreview) {
            PreviewDialog(
                markdown = state.markdownOutput,
                onDismiss = { viewModel.onEvent(HomeEvent.TogglePreview) }
            )
        }
    }
}

@Composable
private fun TopBar(
    onSettingsClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "📱 SpecMD",
                style = MaterialTheme.typography.headlineMedium,
                color = Primary
            )
            Text(
                text = stringResource(id = R.string.app_tagline),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        IconButton(onClick = onSettingsClick) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = stringResource(id = R.string.settings),
                tint = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
private fun ActionButton(
    isCopied: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isCopied) Success else Primary,
            contentColor = if (isCopied) BgBase else TextOnAccent
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Icon(
            imageVector = if (isCopied) Icons.Default.Check else Icons.Default.ContentCopy,
            contentDescription = null,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = if (isCopied) stringResource(id = R.string.toast_copied) else stringResource(id = R.string.action_copy),
            style = MaterialTheme.typography.titleMedium
        )
    }
}
