package data.model;

import data.ui.TileGrid;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Organiser {
    private TileGrid grid;
    private ObjectSorter sorter;

    public Organiser(TileGrid grid) {
        this.grid = grid;
        this.sorter = new ObjectSorter();
    }


}
