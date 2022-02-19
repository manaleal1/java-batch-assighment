package org.homework;

import java.util.*;
import java.util.stream.Collectors;

/*
1. cornercases/requirement
2. design structure
3. OOD with //TODO
3. writing test
5. impl //TODO
6. run test
 */

public class SongCacheImpl implements SongCache{

    private HashMap<String, Integer> songData;

    public SongCacheImpl() {
        this.songData = new HashMap<>();
    }

    @Override
    public void recordSongPlays(String songId, int numPlays) {
        if ( songId == null )
            throw new NullPointerException("SongId can't be null");
        if( numPlays < 0 )
            throw new RuntimeException("numPlays can't be less than zero");
        if( songId.trim().isEmpty() )
            return;
        if ( numPlays == 0 ) {
            songData.remove(songId);
            return;
        }

        if ( songData.containsKey(songId) )
            songData.put(songId, songData.get(songId)+numPlays);
        else
            songData.put(songId, numPlays);

    }

    @Override
    public int getPlaysForSong(String songId) {
        if( songId == null ) throw new NullPointerException("songId can't be null");
        if( songId.trim().isEmpty() ) return -1;
        return (songData.get(songId) == null) ? -1 : songData.get(songId);
    }

    @Override
    public List<String> getTopNSongsPlayed(int n) {
        return songData.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return o2-o1;
                    }
                }))
                .limit(n)
                .map(e-> e.getKey())
                .collect(Collectors.toList());
//                .collect(ArrayList::new, (x,y)-> x.add(y.getKey()), (strings, strings2) -> {});
    }
}
