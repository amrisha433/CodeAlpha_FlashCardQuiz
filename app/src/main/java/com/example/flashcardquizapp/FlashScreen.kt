package com.example.flashcardquizapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.material3.OutlinedTextField
import com.example.flashcardquizapp.model.FlashCard
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
//import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.ui.graphics.Color
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.unit.sp
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight

//@Preview(showBackground = true,showSystemUi = true)
@Composable
fun FlashCardScreen(onFinish: () -> Unit) {
    var showDeleteDialog by rememberSaveable {
        mutableStateOf(false)
    }
    var showEditDialog by rememberSaveable {
        mutableStateOf(false)
    }
    var newQuestion by rememberSaveable {
        mutableStateOf("")
    }
    var newAnswer by rememberSaveable {
        mutableStateOf("")
    }
    var showAnswer by rememberSaveable {
        mutableStateOf(false)
    }
    var currentIndex by rememberSaveable {
        mutableStateOf(0)
    }
    var showAddDialog by rememberSaveable {
        mutableStateOf(false)
    }
    val flashCards = remember {
        mutableStateListOf(
            FlashCard(
            question = "What is the capital of France?",
            answer = "Paris"
        ),
        FlashCard(
            question = "What is Kotlin?",
            answer = "A modern programming language for Android development."
        ),
        FlashCard(
            question = "What is OOP?",
            answer = "Object Oriented Programming."
        ),
        FlashCard(
            question = "What is JVM?",
            answer = "Java Virtual Machine."
        ),
        FlashCard(
            question = "What is Android Studio?",
            answer = "The official IDE for Android app development."
        ),
        FlashCard(
            question = "What is a Variable?",
            answer = "A container used to store data."
        ),
        FlashCard(
            question = "What is a Function?",
            answer = "A reusable block of code that performs a specific task."
        ),
        FlashCard(
            question = "What is Jetpack Compose?",
            answer = "Android's modern toolkit for building native UI."
        ),
        FlashCard(
            question = "What is Git?",
            answer = "A version control system used to track code changes."
        ),
        FlashCard(
            question = "What is GitHub?",
            answer = "A platform for hosting and collaborating on Git repositories."
        )
        )
    }
    if (flashCards.isEmpty()) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text("No Flashcards Available")

            Button(
                onClick = {
                    showAddDialog = true
                }
            ) {
                Text("Add Flashcard")
            }
        }

        if (showAddDialog) {

            AlertDialog(
                onDismissRequest = {
                    showAddDialog = false
                },

                title = {
                    Text("Add Flashcard")
                },

                text = {

                    Column {

                        OutlinedTextField(
                            value = newQuestion,
                            onValueChange = {
                                newQuestion = it
                            },
                            label = {
                                Text("Question")
                            }
                        )

                        Spacer(
                            modifier = Modifier.height(12.dp)
                        )

                        OutlinedTextField(
                            value = newAnswer,
                            onValueChange = {
                                newAnswer = it
                            },
                            label = {
                                Text("Answer")
                            }
                        )
                    }
                },

                confirmButton = {

                    Button(
                        onClick = {

                            if (
                                newQuestion.isNotBlank() &&
                                newAnswer.isNotBlank()
                            ) {

                                flashCards.add(
                                    FlashCard(
                                        question = newQuestion,
                                        answer = newAnswer
                                    )
                                )

                                newQuestion = ""
                                newAnswer = ""

                                showAddDialog = false
                            }
                        }
                    ) {
                        Text("Save")
                    }
                },

                dismissButton = {

                    Button(
                        onClick = {
                            showAddDialog = false
                        }
                    ) {
                        Text("Cancel")
                    }
                }
            )
        }

        if (flashCards.isEmpty()) {

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Text("No Flashcards Available")

                Button(
                    onClick = {
                        showAddDialog = true
                    }
                ) {
                    Text("Add Flashcard")
                }
            }

            return
        }
    }
    val currentCard = flashCards[currentIndex]
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 40.dp, horizontal = 5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "Flashcard Quiz",
                textAlign = TextAlign.Center,style = MaterialTheme.typography.headlineMedium,
                fontSize = 32.sp
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null
            )
        }

        Spacer(modifier = Modifier.height(15.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF1F2937)
            ),
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            )
        ){

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.Start
            ) {

                Text(
                    text = "Card ${currentIndex + 1} of ${flashCards.size}",
                    style = MaterialTheme.typography.labelLarge,
                    color = Color.LightGray
                )

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = "Question",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(12.dp))

                Crossfade(
                    targetState = currentCard
                ) { card ->

                    Text(
                        text = card.question,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = {
                        showAnswer = !showAnswer
                    },
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFDC2626)
                    )
                ) {
                    Text(
                        text = if (showAnswer) "Hide Answer" else "Show Answer"
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                AnimatedVisibility(
                    visible = showAnswer
                ) {

                    Column {

                        Text(
                            text = "Answer",
                            color = Color.Gray
                        )

                        Spacer(
                            modifier = Modifier.height(8.dp)
                        )

                        Text(
                            text = currentCard.answer,
                            color = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.height(40.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    Button(
                        onClick = {
                            if(currentIndex > 0){
                                currentIndex--
                            }
                            showAnswer = false
                        },
                        enabled = currentIndex > 0,
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFDC2626),
                            disabledContainerColor = Color(0xFF7A7A7A)
                        )
                    ) {
                        Text("Previous")
                    }

                    Button(
                        onClick = {
                            if(currentIndex < flashCards.size - 1){
                                currentIndex++
                            }
                        },
                        modifier = Modifier.weight(1f),
                        enabled = currentIndex < flashCards.size - 1,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFDC2626),
                            disabledContainerColor = Color(0xFF7A7A7A)
                        )
                    ) {
                        Text("Next")
                    }
                }
                Button(
                    onClick = {
                        showAddDialog = true
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF16A34A)
                    )
                ) {
                    Text("Add Flashcard")
                }
                if (showAddDialog) {

                    AlertDialog(
                        onDismissRequest = {
                            showAddDialog = false
                        },

                        title = {
                            Text("Add Flashcard")
                        },

                        text = {

                            Column {

                                OutlinedTextField(
                                    value = newQuestion,
                                    onValueChange = {
                                        newQuestion = it
                                    },
                                    label = {
                                        Text("Question")
                                    }
                                )

                                Spacer(
                                    modifier = Modifier.height(12.dp)
                                )

                                OutlinedTextField(
                                    value = newAnswer,
                                    onValueChange = {
                                        newAnswer = it
                                    },
                                    label = {
                                        Text("Answer")
                                    }
                                )
                            }
                        },

                        confirmButton = {

                            Button(
                                onClick = {

                                    if (
                                        newQuestion.isNotBlank() &&
                                        newAnswer.isNotBlank()
                                    ) {

                                        flashCards.add(
                                            FlashCard(
                                                question = newQuestion,
                                                answer = newAnswer
                                            )
                                        )

                                        newQuestion = ""
                                        newAnswer = ""

                                        showAddDialog = false
                                    }
                                }
                            ) {
                                Text("Save")
                            }
                        },

                        dismissButton = {

                            Button(
                                onClick = {
                                    showAddDialog = false
                                }
                            ) {
                                Text("Cancel")
                            }
                        }
                    )
                }

                Button(
                    onClick = {

                        newQuestion = currentCard.question
                        newAnswer = currentCard.answer

                        showEditDialog = true
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF2563EB)
                    )
                ) {
                    Text("Edit Flashcard")
                }
                if (showEditDialog) {

                    AlertDialog(
                        onDismissRequest = {
                            showEditDialog = false
                        },

                        title = {
                            Text("Edit Flashcard")
                        },

                        text = {

                            Column {

                                OutlinedTextField(
                                    value = newQuestion,
                                    onValueChange = {
                                        newQuestion = it
                                    },
                                    label = {
                                        Text("Question")
                                    }
                                )

                                Spacer(
                                    modifier = Modifier.height(12.dp)
                                )

                                OutlinedTextField(
                                    value = newAnswer,
                                    onValueChange = {
                                        newAnswer = it
                                    },
                                    label = {
                                        Text("Answer")
                                    }
                                )
                            }
                        },

                        confirmButton = {

                            Button(
                                onClick = {

                                    flashCards[currentIndex] = FlashCard(
                                        question = newQuestion,
                                        answer = newAnswer
                                    )

                                    showEditDialog = false

                                    newQuestion = ""
                                    newAnswer = ""
                                }
                            ) {
                                Text("Update")
                            }
                        },

                        dismissButton = {

                            Button(
                                onClick = {
                                    showEditDialog = false
                                }
                            ) {
                                Text("Cancel")
                            }
                        }
                    )
                }
                Button(
                    onClick = {
                        showDeleteDialog = true


                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFF97316)
                    )
                ) {
                    Text("Delete Flashcard")
                }
                if (showDeleteDialog) {

                    AlertDialog(
                        onDismissRequest = {
                            showDeleteDialog = false
                        },

                        title = {
                            Text("Delete Flashcard")
                        },

                        text = {
                            Text(
                                "Are you sure you want to delete this flashcard?"
                            )
                        },

                        confirmButton = {

                            Button(
                                onClick = {

                                    flashCards.removeAt(currentIndex)

                                    if (flashCards.isNotEmpty()) {

                                        if (currentIndex >= flashCards.size) {
                                            currentIndex = flashCards.lastIndex
                                        }

                                    } else {
                                        currentIndex = 0
                                    }

                                    showAnswer = false
                                    showDeleteDialog = false
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFFDC2626)
                                )
                            ) {
                                Text("Delete")
                            }
                        },

                        dismissButton = {

                            Button(
                                onClick = {
                                    showDeleteDialog = false
                                }
                            ) {
                                Text("Cancel")
                            }
                        }
                    )
                }
                Spacer(modifier = Modifier.height(50.dp))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.End
                    //verticalArrangement = Arrangement.Bottom
                ) {
                    Button(
                        onClick = {
                            onFinish()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF4CAF50)
                        )
                    ) {
                        Text("Finish")
                    }
                }

            }
        }
    }
}