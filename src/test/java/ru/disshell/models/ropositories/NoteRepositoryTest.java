package ru.disshell.models.ropositories;

import org.junit.Test;
import ru.disshell.models.Note;
import ru.disshell.models.User;
import ru.disshell.models.repositories.NoteRepository;
import ru.disshell.models.repositories.UserRepository;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class NoteRepositoryTest {

    @Test
    public void TestNotes(){
        final NoteRepository noteRepository = new NoteRepository();
        final UserRepository userRepository = new UserRepository();
        User user = userRepository.get(1);
        Note note = new Note();
        note.setUser(user);
        note.setDate(new Date());
        note.setText("1234");

        final int id = noteRepository.add(note);
        note  = noteRepository.get(id);
        assertEquals(id , note.getId());
        assertEquals("1234" , note.getText() );
        note.setText("4321");
        noteRepository.edit(note);
        assertEquals(id , note.getId());
        assertEquals("4321" , note.getText() );
        noteRepository.delete(id);
        assertNull(noteRepository.get(id));
    }
}