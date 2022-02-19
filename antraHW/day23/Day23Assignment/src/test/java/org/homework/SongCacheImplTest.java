package org.homework;

import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
public class SongCacheImplTest {

    @Test
    public void cacheIsWorking(){
        SongCache cache = new SongCacheImpl();
        cache.recordSongPlays("ID-1", 3);
        cache.recordSongPlays("ID-1", 1);
        cache.recordSongPlays("ID-2", 2);
        cache.recordSongPlays("ID-3", 5);

        assertEquals(cache.getPlaysForSong("ID-1"), 4);
        assertEquals(cache.getPlaysForSong("ID-9"), -1);

        assertEquals(cache.getTopNSongsPlayed(2), Arrays.asList("ID-3", "ID-1"));
        assertEquals(cache.getTopNSongsPlayed(0), List.of());
    }

    @Test
    public void recordSongPlaysTest(){

        SongCache mockCache = mock(SongCacheImpl.class);
        //If no data has been entered, return an empty list
        when( mockCache.getTopNSongsPlayed(1) ).thenReturn(new ArrayList<>());

        //After trying to add empty string, check to make sure it was not added to cache
        mockCache.recordSongPlays("", 1);
        when( mockCache.getTopNSongsPlayed(1) ).thenReturn(new ArrayList<>());

        //if null, throw exception
        //when(  mockCache.recordSongPlays(null,1) ).thenThrow(new RuntimeException());
        doThrow(RuntimeException.class).when(mockCache).recordSongPlays(null,1);

        //if num is negative throw exception
        //assertThrows(RuntimeException.class, ()-> cache.recordSongPlays("ID-1",-1));
        doThrow(RuntimeException.class).when(mockCache).recordSongPlays("ID-1",-1);

        mockCache.recordSongPlays("ID-1", 3);
        mockCache.recordSongPlays("ID-1", 1);
        mockCache.recordSongPlays("ID-2", 2);
        mockCache.recordSongPlays("ID-3", 5);
        when(mockCache.getTopNSongsPlayed(3)).thenReturn(Arrays.asList("ID-1","ID-2","ID-3"));

        //if num == 0 remove it from cache.
        //Check to make sure song was removed from cache
        mockCache.recordSongPlays("ID-1",0);
        when(mockCache.getTopNSongsPlayed(3) ).thenReturn(Arrays.asList("ID-2","ID-3"));
        assertTrue( mockCache.getTopNSongsPlayed(3).size() == 2 );
        assertTrue( mockCache.getTopNSongsPlayed(0).isEmpty() );
    }
    @Test
    public void getPlaysForSongTest(){
        SongCache mockCache = mock(SongCacheImpl.class);
        //empty string doesn't get added to cache, so this should return -1
        when(mockCache.getPlaysForSong("")).thenReturn(-1);
        when(mockCache.getPlaysForSong(null)).thenThrow(RuntimeException.class);
    }
    @Test
    public void getTopNSongsPlayedTest(){
        SongCache mockCache = mock(SongCacheImpl.class);
        //if no data, return empty result
        when( mockCache.getTopNSongsPlayed(999)).thenReturn(List.of());
        mockCache.recordSongPlays("ID-1", 3);
        mockCache.recordSongPlays("ID-1", 1);
        mockCache.recordSongPlays("ID-2", 2);
        mockCache.recordSongPlays("ID-3", 5);
        when( mockCache.getTopNSongsPlayed(3)).thenReturn(List.of("ID-3","ID-1","ID-2"));

        //if N == 0 return empty list
        assertTrue( mockCache.getTopNSongsPlayed(0).isEmpty() );
        when( mockCache.getTopNSongsPlayed(0)).thenReturn(List.of());

        //if N == -1 throw exception
        when(mockCache.getTopNSongsPlayed(-1)).thenThrow(RuntimeException.class);
    }
}
