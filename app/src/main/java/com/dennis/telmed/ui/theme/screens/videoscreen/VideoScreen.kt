package com.dennis.telmed.ui.theme.screens.videoscreen

import android.Manifest
import android.content.pm.PackageManager
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CallEnd
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.MicOff
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material.icons.filled.VideocamOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import io.agora.rtc2.Constants
import io.agora.rtc2.IRtcEngineEventHandler
import io.agora.rtc2.RtcEngine
import io.agora.rtc2.video.VideoCanvas

@Composable
fun VideoCallScreen(
    channelName: String,
    appId: String,
    token: String? = null,
    uid: Int = 0,
    onCallEnd: () -> Unit // Callback to exit screen
) {
    val context = LocalContext.current
    var hasPermissions by remember { mutableStateOf(false) }
    var isJoined by remember { mutableStateOf(false) }
    var isMuted by remember { mutableStateOf(false) }
    var isVideoOn by remember { mutableStateOf(true) }
    var remoteUid by remember { mutableStateOf<Int?>(null) } // Track remote user

    // Check permissions
    LaunchedEffect(Unit) {
        val permissions = listOf(
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.CAMERA
        )
        hasPermissions = permissions.all {
            ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
        }
    }

    if (!hasPermissions) {
        Text("Please allow camera & microphone permissions!")
        return
    }

    // Initialize Agora Engine
    val rtcEngine = remember {
        RtcEngine.create(context, appId, object : IRtcEngineEventHandler() {
            override fun onJoinChannelSuccess(channel: String?, uid: Int, elapsed: Int) {
                isJoined = true
            }

            override fun onUserJoined(uid: Int, elapsed: Int) {
                remoteUid = uid // Remote user joined!
            }

            override fun onUserOffline(uid: Int, reason: Int) {
                remoteUid = null // Remote user left
            }
        }).apply {
            enableVideo()
            setupLocalVideo(VideoCanvas(null, Constants.RENDER_MODE_HIDDEN, 0))
        }
    }

    // Join channel
    LaunchedEffect(Unit) {
        rtcEngine.joinChannel(token, channelName, "", uid)
    }

    // Cleanup
    DisposableEffect(Unit) {
        onDispose {
            rtcEngine.leaveChannel()
            RtcEngine.destroy()
        }
    }

    // Toggle Mic
    fun toggleMic() {
        isMuted = !isMuted
        rtcEngine.muteLocalAudioStream(isMuted)
    }

    // Toggle Camera
    fun toggleCamera() {
        isVideoOn = !isVideoOn
        rtcEngine.muteLocalVideoStream(!isVideoOn)
    }

    // End Call
    fun endCall() {
        rtcEngine.leaveChannel()
        onCallEnd() // Close screen
    }

    // UI
    Box(modifier = Modifier.fillMaxSize()) {
        // Remote Video (Big Screen)
        if (remoteUid != null) {
            AndroidView(
                factory = { ctx ->
                    val surfaceView = RtcEngine.CreateRendererView(ctx)
                    rtcEngine.setupRemoteVideo(
                        VideoCanvas(
                            surfaceView,
                            Constants.RENDER_MODE_HIDDEN,
                            remoteUid!!
                        )
                    )
                    surfaceView
                },
                modifier = Modifier.fillMaxSize()
            )
        }

        // Local Video (Small Overlay)
        AndroidView(
            factory = { ctx ->
                val surfaceView = RtcEngine.CreateRendererView(ctx)
                rtcEngine.setupLocalVideo(
                    VideoCanvas(
                        surfaceView,
                        Constants.RENDER_MODE_HIDDEN,
                        0
                    )
                )
                surfaceView
            },
            modifier = Modifier
                .size(120.dp)
                .align(Alignment.TopEnd)
                .padding(16.dp)
        )

        // Control Buttons (Bottom Bar)
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Mic Toggle
            IconButton(onClick = { toggleMic() }) {
                Icon(
                    imageVector = if (isMuted) Icons.Default.MicOff else Icons.Default.Mic,
                    contentDescription = if (isMuted) "Unmute" else "Mute",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }

            // End Call Button
            FilledIconButton(
                onClick = { endCall() },
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = MaterialTheme.colorScheme.error
                )
            ) {
                Icon(
                    imageVector = Icons.Default.CallEnd,
                    contentDescription = "End Call",
                    tint = MaterialTheme.colorScheme.onError
                )
            }

            // Camera Toggle
            IconButton(onClick = { toggleCamera() }) {
                Icon(
                    imageVector = if (isVideoOn) Icons.Default.Videocam else Icons.Default.VideocamOff,
                    contentDescription = if (isVideoOn) "Turn Off Camera" else "Turn On Camera",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}