package assign09;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public abstract class CodeTimer {
    private int steps = 10;
    private int start = 100000;
    private int loops = 1000;
    private int stepSize = 0;
    private boolean printSteps = true;

    public CodeTimer() {
        Settings settings = getClass().getAnnotation(CodeTimer.Settings.class);
        if (settings != null) {
            steps = settings.steps();
            loops = settings.loops();
            start = settings.start();
            stepSize = settings.stepSize();
            printSteps = settings.printSteps();
            System.out.println(settings.title());
            System.out.println(settings.titleY() + "\t" + settings.titleX());
        }
        stepSize = stepSize == 0 ? start : stepSize;
        if (stepSize < 0) throw new IllegalArgumentException("stepSize is less than zero!");
        if (start < 0) throw new IllegalArgumentException("start is less than zero!");
        if (steps < 0) throw new IllegalArgumentException("steps is less than zero!");
        runTimer();
    }

    /**
     * Code that is ran before the code being timed
     *
     * @param steps Current steps
     */
    public void before(int steps) {
    }

    /**
     * Code that is being timed
     *
     * @param steps Current steps
     */
    public abstract void timedCode(int steps);

    /**
     * Extra code used to assist the timed code (Avoid if possible)
     *
     * @param steps Current steps
     */
    public void extra(int steps) {
    }

    public void output(int step, double avgTime) {
        if (printSteps) {
            System.out.println(step + "\t" + String.format("%.3f", avgTime/100));
        } else {
            System.out.println(String.format("%.3f", avgTime/1000));
        }
    }

    private void runTimer() {
        for (int step = start; step < start + (steps * stepSize); step += stepSize) {
            before(step);

            long start, middle, end;

            start = System.nanoTime();
            while(System.nanoTime() - start < 1000000000) { // empty block
            }

            start = System.nanoTime();
            for (int n = 0; n < loops; n++) {
                timedCode(step);
            }
            middle = System.nanoTime();
            for (int n = 0; n < loops; n++) {
                extra(step);
            }
            end = System.nanoTime();

            double avgTime = ((middle - start) - (end - middle)) / (double) loops;

            output(step, avgTime);
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface Settings {
        String titleY();
        String titleX();
        String title();

        int start() default 1000;

        int steps() default 20;

        int loops() default 1000;

        int stepSize() default 0;

        boolean printSteps() default true;
    }
}

