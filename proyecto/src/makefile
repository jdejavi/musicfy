all:
	javac -cp /home/javier/Desktop/lib/java/biblioteca.jar apppracticafinal/AppPracticaFinal.java view/View.java controller/Controller.java data/Musicfy.java data/Song.java data/Album.java data/Artist.java data/PlayList.java data/Model.java 
	jar cvmf manifest.mf Musicfy.jar /home/javier/Desktop/lib/java/biblioteca.jar apppracticafinal/AppPracticaFinal.class view/View.class controller/Controller.class data/Musicfy.class data/Song.class data/Album.class data/Artist.class data/PlayList.class data/Model.class
	
clean:
	rm controller/*.class data/*.class musicfy/*.class view/*.class

