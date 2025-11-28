package moje.OOP.inheritance.Hospital;

public class Hospital {
    public static void main(String[] args) {
        Doctor doc = new Doctor("Karel");
        System.out.println(doc);

        Surgeon sur = new Surgeon("Pepa");
        System.out.println(sur);

        Doctor[] personnel = {doc, sur};

        System.out.println("Je chirurg doctor? " + (sur instanceof Doctor));

        CardiacSurgeon legitOne = new CardiacSurgeon("Alfred");
        Doctor fakeOne = new CardiacSurgeon("Lukas");

        System.out.println(fakeOne);
        // donutíme ho jak dělat surgery a cardiatic surgery
        int a = (int) 4.5;
        //varianta 1: ulozim do promneny, kde uz to bude umet
        CardiacSurgeon reference = (CardiacSurgeon) fakeOne;
        reference.cardiacsurgery();
        //fuj

        // var2: dočasně ho přetypuju a zároveň něco udělej to co normálně nemůžeš - hezké
        ((CardiacSurgeon)fakeOne).cardiacsurgery();

        Doctor[] alsoPersonnel = {doc, sur, legitOne, fakeOne};
        for (int i = 0; i < alsoPersonnel.length; i++) {
            //zkuste všichni diagnostikovat
            alsoPersonnel[i].diagnose();
            //tohle zkasi bohuzel ten jenom doctor
            // ((Surgeon)alsoPersonnel[i]).surgery();
            //ohlídám si že může:
            if (alsoPersonnel[i] instanceof Surgeon){
                ((Surgeon)alsoPersonnel[i]).surgery();
            }


        }


    }
}
