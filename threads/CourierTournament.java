package threads;

import animals.Animal;
import animals.AnimalThread;

import java.util.Vector;

public class CourierTournament extends Tournament {
    private Vector<Thread> courier_threads = new Vector<>();
    private int max = 30;
    private Boolean start_Flag;
    private Boolean finish_Flag;

    public CourierTournament(Animal[][] setup_arr){
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
    }

    public void set_threads(Animal[][] setup_arr,Animal animal, int i){
        setup_arr[0][i] = animal;
        AnimalThread temp = new AnimalThread(animal, start_Flag , finish_Flag);
        Thread temp_thread = new Thread(temp);
        courier_threads.set(i,temp_thread);
        courier_threads.get(i).start();
    }

    public void init_threads(){
        for (int i=0; i < max; ++i){
            courier_threads.add(i,new Thread());
        }
    }

    public void stop_threads(){
        for (int i=0; i < max; ++i){
            courier_threads.get(i).stop();
        }
    }
}
