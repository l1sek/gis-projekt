package pl.edu.pw.elka.gis.LGraph.core.process;

import pl.edu.pw.elka.gis.LGraph.core.action.Action;
import pl.edu.pw.elka.gis.LGraph.core.action.ActionListener;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by mmajewski on 2016-12-17.
 */
public abstract class RunnableActionListener<T extends ActionListener> implements ActionListener<T>, Runnable {
    private BlockingQueue<Action<T>> actionQueue = new ArrayBlockingQueue<>(100);

    @Override
    public void registerAction(Action<T> action) {
        try {
            actionQueue.put(action);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                Action<T> action = actionQueue.take();
                action.apply((T) this);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void runInForeground(){
        Queue<Action<T>> foregroundQueue = new ArrayDeque<>();
        actionQueue.drainTo(foregroundQueue);
        for (Action<T> action : foregroundQueue){
            action.apply((T) this);
        }
    }
}
