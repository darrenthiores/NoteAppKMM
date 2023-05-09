package com.dev.noteappkmm.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dev.noteappkmm.android.note_detail.NoteDetailScreen
import com.dev.noteappkmm.android.note_list.NoteListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = NoteAppScreens.NoteList.name
                ) {
                    composable(NoteAppScreens.NoteList.name) {
                        NoteListScreen(
                            onNavigateToDetail = {
                                navController.navigate("${NoteAppScreens.NoteDetail.name}/$it")
                            }
                        )
                    }

                    composable(
                        route = "${NoteAppScreens.NoteDetail.name}/{noteId}",
                        arguments = listOf(
                            navArgument(name = "noteId") {
                                type = NavType.LongType
                                defaultValue = -1L
                            }
                        )
                    ) { navBackStackEntry ->
                        val noteId = navBackStackEntry.arguments?.getLong("noteId") ?: -1

                        NoteDetailScreen(
                            noteId = noteId,
                            onSaveClicked = {
                                navController.popBackStack()
                            }
                        )
                    }
                }
            }
        }
    }
}
