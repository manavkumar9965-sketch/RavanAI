package com.ravan.ai.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ravan.ai.domain.model.Command
import com.ravan.ai.domain.router.CommandRouter
import com.ravan.ai.domain.router.RouteDecision

@Composable
fun HomePlaceholderScreen() {
    var input by remember { mutableStateOf("") }
    var result by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "RAVAN AI",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Command router test (Stage 3). Voice Stage 4 mein aayega.",
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 14.sp
        )
        OutlinedTextField(
            value = input,
            onValueChange = { input = it },
            label = { Text("Command likho") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = { result = describe(CommandRouter.route(input)) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Route karo")
        }
        result?.let {
            Text(text = it, color = MaterialTheme.colorScheme.onBackground, fontSize = 16.sp)
        }
    }
}

private fun describe(decision: RouteDecision): String = when (decision) {
    is RouteDecision.Local -> "Local command mila: ${describeCommand(decision.command)}"
    is RouteDecision.Clarify -> "RAVAN poochta hai: ${decision.question}"
    is RouteDecision.SendToAi -> "Local match nahi hua. Gemini ke paas jaayega (Stage 6): \"${decision.text}\""
}

private fun describeCommand(command: Command): String = when (command) {
    is Command.OpenApp -> "App kholo: ${command.appName}"
    is Command.OpenWebsite -> "Website kholo: ${command.site}"
    Command.TorchOn -> "Torch ON"
    Command.TorchOff -> "Torch OFF"
    Command.VolumeUp -> "Volume badhao"
    Command.VolumeDown -> "Volume kam karo"
    Command.BatteryStatus -> "Battery status"
    is Command.SetAlarm -> "Alarm: %02d:%02d".format(command.hour, command.minute)
    is Command.Reminder -> "Reminder: ${command.minutes} minute baad, \"${command.message}\""
    Command.GoBack -> "Back"
    Command.GoHome -> "Home"
}
