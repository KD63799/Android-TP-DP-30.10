package com.example.android_tp_dp_3010

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.android_tp_dp_3010.ui.theme.AndroidTPDP3010Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidTPDP3010Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ProfilScreen(
                        name = "kd63799",
                        role = "Développeur / DevOps",
                        description = "En poste depuis décembre 2024, je prépare en parallèle " +
                            "un master en architecture logicielle à l'ESGI.",
                        skills = listOf("Kotlin", "Jetpack Compose", "Git", "NestJS"),
                        email = "kd63799@example.com",
                        availability = "En poste depuis décembre 2024 à Marseille, ouvert aux échanges",
                        isAvailable = true,
                        onContactClick = {
                            // Le composant signale seulement le clic, la suite n'est pas son rôle.
                        },
                        onAvailabilityClick = {
                            // Idem : rien à faire pour ce devoir.
                        },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ProfilScreen(
    name: String,
    role: String,
    description: String,
    skills: List<String>,
    email: String,
    availability: String,
    isAvailable: Boolean,
    onContactClick: () -> Unit,
    onAvailabilityClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        ProfileCard(
            name = name,
            role = role,
            description = description,
            skills = skills,
            email = email,
            availability = availability,
            isAvailable = isAvailable,
            onContactClick = onContactClick,
            onAvailabilityClick = onAvailabilityClick,
            modifier = Modifier.padding(16.dp) // padding externe : décidé par le parent
        )
    }
}

@Composable
fun ProfileCard(
    name: String,
    role: String,
    description: String,
    skills: List<String>,
    email: String,
    availability: String,
    isAvailable: Boolean,
    onContactClick: () -> Unit,
    onAvailabilityClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surfaceVariant,
        shape = MaterialTheme.shapes.large
    ) {
        Column(modifier = Modifier.padding(16.dp)) { // padding interne : espace le contenu
            ProfileHeader(name = name, role = role)
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 12.dp)
            )
            Text(
                text = "Compétences",
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(top = 16.dp, bottom = 4.dp)
            )
            skills.forEach { skill ->
                SkillTag(skill = skill, modifier = Modifier.padding(vertical = 3.dp))
            }
            InfoLine(
                label = "Contact",
                value = email,
                modifier = Modifier.padding(top = 16.dp)
            )
            AvailabilityZone(
                text = availability,
                isAvailable = isAvailable,
                onClick = onAvailabilityClick,
                modifier = Modifier.padding(top = 8.dp)
            )
            ContactButton(
                text = "Contacter",
                onClick = onContactClick,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}

@Composable
fun ProfileHeader(name: String, role: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(text = name, style = MaterialTheme.typography.headlineMedium)
        Text(
            text = role,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun SkillTag(skill: String, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.secondaryContainer,
        shape = MaterialTheme.shapes.small
    ) {
        Text(
            text = skill,
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
        )
    }
}

@Composable
fun InfoLine(label: String, value: String, modifier: Modifier = Modifier) {
    Text(
        text = "$label : $value",
        style = MaterialTheme.typography.bodySmall,
        modifier = modifier
    )
}

@Composable
fun AvailabilityZone(
    text: String,
    isAvailable: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val statut = if (isAvailable) "Disponible" else "Non disponible"
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clickable(role = Role.Button, onClick = onClick)
            .semantics { contentDescription = "$statut : $text. Appuyer pour voir les détails" },
        color = if (isAvailable) {
            MaterialTheme.colorScheme.secondaryContainer
        } else {
            MaterialTheme.colorScheme.errorContainer
        },
        shape = MaterialTheme.shapes.medium
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(12.dp)
        )
    }
}

@Composable
fun ContactButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(onClick = onClick, modifier = modifier.fillMaxWidth()) {
        Text(text = text)
    }
}

@Preview(showBackground = true, name = "Android junior disponible")
@Composable
fun ProfilJuniorPreview() {
    AndroidTPDP3010Theme {
        ProfilScreen(
            name = "kd63799",
            role = "Développeur Android junior",
            description = "Étudiant en informatique, je construis mes premières " +
                "applications avec Jetpack Compose et je cherche une alternance.",
            skills = listOf("Kotlin", "Jetpack Compose", "Git"),
            email = "kd63799@example.com",
            availability = "Disponible pour une alternance dès septembre",
            isAvailable = true,
            onContactClick = {},
            onAvailabilityClick = {}
        )
    }
}

@Preview(showBackground = true, name = "Full-stack non disponible")
@Composable
fun ProfilFullStackPreview() {
    AndroidTPDP3010Theme {
        ProfilScreen(
            name = "dev-fullstack-01",
            role = "Développeur full-stack",
            description = "Cinq ans d'expérience sur des API REST et des interfaces web.",
            skills = listOf("Kotlin", "TypeScript", "PostgreSQL", "Docker"),
            email = "fullstack01@example.com",
            availability = "Indisponible jusqu'en janvier",
            isAvailable = false,
            onContactClick = {},
            onAvailabilityClick = {}
        )
    }
}
