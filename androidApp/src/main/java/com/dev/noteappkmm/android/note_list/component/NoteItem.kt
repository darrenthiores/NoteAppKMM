package com.dev.noteappkmm.android.note_list.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dev.noteappkmm.domain.models.Note
import com.dev.noteappkmm.domain.time.DateTimeUtil

@Composable
fun NoteItem(
    modifier: Modifier = Modifier,
    note: Note,
    backgroundColor: Color,
    onNoteClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    val formattedDate = remember(note.created) {
        DateTimeUtil.formatNoteDate(note.created)
    }

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(5.dp))
            .background(backgroundColor)
            .clickable { onNoteClick() }
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.subtitle2.copy(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )

            Icon(
                modifier = Modifier
                    .clickable(
                        MutableInteractionSource(),
                        null
                    ) { onDeleteClick() },
                imageVector = Icons.Default.Close,
                contentDescription = "Delete Note"
            )
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = note.content,
            style = MaterialTheme.typography.caption.copy(
                fontWeight = FontWeight.Light
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            modifier = Modifier
                .align(Alignment.End),
            text = formattedDate,
            color = Color.DarkGray
        )
    }
}