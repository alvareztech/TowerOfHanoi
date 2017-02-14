package tech.alvarez.towerofhanoi;

import java.util.Stack;

public class Main {

    public static void main(String args[]) {
        new MainFrame();
//        algoritmoHanoi(6, 1, 2, 3);
//        System.out.println("");
//        algoritmoHanoi(6);
    }

    public static void algoritmoHanoi(int n, int origen, int temporal, int destino) {
        if (n == 0) {
            return;
        }
        algoritmoHanoi(n - 1, origen, destino, temporal);
        System.out.println("Mover " + n + " de la torre " + origen + " a la torre " + destino + ".");
        algoritmoHanoi(n - 1, temporal, origen, destino);
    }

    public static void algoritmoHanoi(int n) {
        Stack<Integer>[] torres = new Stack[4];
        torres[1] = new Stack<Integer>();
        torres[2] = new Stack<Integer>();
        torres[3] = new Stack<Integer>();
        for (int i = n; i >= 1; i--) {
            torres[1].push(i);
        }
        int ficha = 0;
        if (n % 2 == 1) {
            ficha = torres[1].pop();
            torres[3].push(ficha);
            System.out.println("Mover " + ficha + " de la torre " + 1 + " a la torre " + 3 + ".");
        } else {
            ficha = torres[1].pop();
            torres[2].push(ficha);
            System.out.println("Mover " + ficha + " de la torre " + 1 + " a la torre " + 2 + ".");
        }

        for (int i = 2; i < (int) Math.pow(2, n); i++) {
            if (i % 2 == 1) {
                //hallar el menor
                int men = 99;
                int torre = 0;
                if (!torres[1].isEmpty() && torres[1].peek() < men) {
                    men = torres[1].peek();
                    torre = 1;
                }
                if (!torres[2].isEmpty() && torres[2].peek() < men) {
                    men = torres[2].peek();
                    torre = 2;
                }
                if (!torres[3].isEmpty() && torres[3].peek() < men) {
                    men = torres[3].peek();
                    torre = 3;
                }
                ficha = torres[torre].pop();
                if (n % 2 == 1) {
                    if (torre == 1) {
                        torres[3].push(ficha);
                        System.out.println("Mover " + ficha + " de la torre " + torre + " a la torre " + 3 + ".");
                    } else {
                        torres[torre - 1].push(ficha);
                        System.out.println("Mover " + ficha + " de la torre " + torre + " a la torre " + (torre - 1) + ".");
                    }
                } else {
                    if (torre == 3) {
                        torres[1].push(ficha);
                        System.out.println("Mover " + ficha + " de la torre " + torre + " a la torre " + 1 + ".");
                    } else {
                        torres[torre + 1].push(ficha);
                        System.out.println("Mover " + ficha + " de la torre " + torre + " a la torre " + (torre + 1) + ".");
                    }
                }
            } else {
                boolean sw = true;
                if (!torres[1].isEmpty() && torres[1].peek() != ficha && sw) {
                    if (torres[2].isEmpty()) {
                        ficha = torres[1].pop();
                        torres[2].push(ficha);
                        System.out.println("Mover " + ficha + " de la torre " + 1 + " a la torre " + 2 + ".");
                        sw = false;
                    } else {
                        if (sw && torres[1].peek() < torres[2].peek()) {
                            ficha = torres[1].pop();
                            torres[2].push(ficha);
                            System.out.println("Mover " + ficha + " de la torre " + 1 + " a la torre " + 2 + ".");
                            sw = false;
                        }
                    }
                    if (torres[3].isEmpty()) {
                        ficha = torres[1].pop();
                        torres[3].push(ficha);
                        System.out.println("Mover " + ficha + " de la torre " + 1 + " a la torre " + 3 + ".");
                        sw = false;
                    } else {
                        if (sw && torres[1].peek() < torres[3].peek()) {
                            ficha = torres[1].pop();
                            torres[3].push(ficha);
                            System.out.println("Mover " + ficha + " de la torre " + 1 + " a la torre " + 3 + ".");
                            sw = false;
                        }
                    }
                }
                if (!torres[2].isEmpty() && torres[2].peek() != ficha && sw) {
                    if (torres[1].isEmpty()) {
                        ficha = torres[2].pop();
                        torres[1].push(ficha);
                        System.out.println("Mover " + ficha + " de la torre " + 2 + " a la torre " + 1 + ".7897");
                        sw = false;
                    } else {
                        if (sw && torres[2].peek() < torres[1].peek()) {
                            ficha = torres[2].pop();
                            torres[1].push(ficha);
                            System.out.println("Mover " + ficha + " de la torre " + 2 + " a la torre " + 1 + ".");
                            sw = false;
                        }
                    }
                    if (torres[3].isEmpty()) {
                        ficha = torres[2].pop();
                        torres[3].push(ficha);
                        System.out.println("Mover " + ficha + " de la torre " + 2 + " a la torre " + 3 + ".");
                        sw = false;
                    } else {
                        if (sw && torres[2].peek() < torres[3].peek()) {
                            ficha = torres[2].pop();
                            torres[3].push(ficha);
                            System.out.println("Mover " + ficha + " de la torre " + 2 + " a la torre " + 3 + ".");
                            sw = false;
                        }
                    }
                }
                if (!torres[1].isEmpty() && torres[1].peek() != ficha && sw) {
                    if (torres[1].isEmpty()) {
                        ficha = torres[3].pop();
                        torres[1].push(ficha);
                        System.out.println("Mover " + ficha + " de la torre " + 3 + " a la torre " + 1 + ".");
                        sw = false;
                    } else {
                        if (sw && torres[3].peek() < torres[1].peek()) {
                            ficha = torres[3].pop();
                            torres[1].push(ficha);
                            System.out.println("Mover " + ficha + " de la torre " + 3 + " a la torre " + 1 + ".");
                            sw = false;
                        }
                    }
                    if (torres[2].isEmpty()) {
                        ficha = torres[3].pop();
                        torres[2].push(ficha);
                        System.out.println("Mover " + ficha + " de la torre " + 3 + " a la torre " + 2 + ".");
                        sw = false;
                    } else {
                        if (sw && torres[3].peek() < torres[2].peek()) {
                            ficha = torres[3].pop();
                            torres[2].push(ficha);
                            System.out.println("Mover " + ficha + " de la torre " + 3 + " a la torre " + 2 + ".");
                            sw = false;
                        }
                    }
                }
            }
        }
    }
}
