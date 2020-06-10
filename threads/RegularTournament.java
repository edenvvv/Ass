package threads;

import animals.Animal;
import animals.AnimalThread;

import java.util.Vector;

public class RegularTournament extends Tournament {
    private Vector<Thread> regular_threads = new Vector<>();
    private int max = 30;
    private Boolean start_Flag;
    private Boolean finish_Flag;

    public RegularTournament(Animal[][] setup_arr){
        super(setup_arr);
        this.start_Flag = false;
        this.finish_Flag = false;
    }
    @Override
    public void setup(Animal[][] setup_arr) {
        Boolean startFlag = false;
        Scores scores = new Scores();

        for (int i=0; i < setup_arr.length; ++i){
            Boolean finishFlag = false;
            AnimalThread temp = new AnimalThread(setup_arr[i][0], startFlag , finishFlag);
            Thread temp_thread = new Thread(temp);
            Referee temp_referee = new Referee(String.valueOf(i+1),scores,finishFlag);
        }
        /*
        special.add(startFlag);
        special.add(scores);
        special.add(setup_arr.length);
        */
    }

    public void start_threads(){
        for (int i = 0; i < regular_threads.size(); ++i){
            regular_threads.get(i).start();
        }
    }

    public void start_lastElement_threads(){
        regular_threads.lastElement().start();
    }

    public void set_threads(Animal[][] setup_arr,Animal animal, int i){
        setup_arr[i][0] = animal;
        AnimalThread temp = new AnimalThread(animal, start_Flag , finish_Flag);
        Thread temp_thread = new Thread(temp);
        regular_threads.set(i,temp_thread);
        regular_threads.get(i).start();
    }

    public void init_threads(){
        for (int i=0; i < max; ++i){
            regular_threads.add(i,new Thread());
        }
    }

    public void stop_threads(){
        for (int i=0; i < max; ++i){
            regular_threads.get(i).stop();
        }
    }


}
