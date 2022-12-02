package com.feldjoshuanoah.gameengine.util;

import com.google.common.collect.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * An implementation of Kahn's algorithm for topological sorting. Topological sorting of a directed
 * graph is a linear ordering of its vertices such that for every directed edge {@code uv} from
 * vertex {@code u} to vertex {@code v}, {@code u} comes before {@code v} in the ordering.
 *
 * @param <T> The vertex type.
 */
public class TopologicalSorter<T> {

    /**
     * The isolated vertices.
     */
    private final Set<T> isolated;

    /**
     * The edges.
     */
    private final ListMultimap<T, T> edges;

    /**
     * The number of incoming edges for each vertex.
     */
    private final Map<T, Integer> incoming;

    /**
     * Creates a new {@code TopologicalSorter} instance.
     */
    public TopologicalSorter() {
        isolated = Sets.newLinkedHashSet();
        edges = ArrayListMultimap.create();
        incoming = Maps.newLinkedHashMap();
    }

    /**
     * Adds a vertex.
     *
     * @param vertex The vertex to add.
     */
    public void addVertex(final T vertex) {
        isolated.add(vertex);
    }

    /**
     * Adds vertices.
     *
     * @param vertices The vertices to add.
     */
    public void addVertices(final Collection<T> vertices) {
        isolated.addAll(vertices);
    }

    /**
     * Adds an edge.
     *
     * @param source The source vertex.
     * @param target The target vertex.
     */
    public void addEdge(final T source, final T target) {
        isolated.remove(target);
        edges.put(source, target);
        incoming.put(target, incoming.getOrDefault(target, 0) + 1);
    }

    /**
     * Sorts the directed graph topologically using Kahn's algorithm.
     *
     * @return A topologically sorted list of vertices.
     */
    public List<T> sort() {
        final List<T> sorted = Lists.newArrayList();
        while (!isolated.isEmpty()) {
            final T vertex = isolated.iterator().next();
            isolated.remove(vertex);
            sorted.add(vertex);
            edges.removeAll(vertex).forEach(dependent -> {
                final int count = incoming.getOrDefault(dependent, 1) - 1;
                incoming.put(dependent, count);
                if (count == 0) {
                    isolated.add(dependent);
                }
            });
        }
        if (!edges.isEmpty()) {
            throw new UnsupportedOperationException();
        }
        return sorted;
    }
}
