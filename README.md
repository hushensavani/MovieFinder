# MovieFinder
Finds movies on your storage media such as HDD, pen drive, mobile storage etc. and fetches the movie details from IMDB.

![MovieFinder](https://cloud.githubusercontent.com/assets/1409894/21981827/4b3253ec-dc0f-11e6-8f57-4e2ad0e1ff32.png)

### How to run
Clone this project and load in your favorite IDE as Maven project. Before you run the project, you need to insert your storage media in your computer and then provide your storage media directory path for which you want to scan the movies, as specified in configuration section.

![Media Path](https://cloud.githubusercontent.com/assets/1409894/21981996/377d2970-dc10-11e6-8c2c-1b82045b54cc.png)



### Configurations
The root directory of your media storage needs to be specified against property **media.root.path** in *config.properties* file available at *src/main/resources*.

![config.properties](https://cloud.githubusercontent.com/assets/1409894/21966239/e9669d9a-db95-11e6-8563-b83690f816dd.png)

### Output
To run the program, the main method is available in service class named **MovieFinderService** available at *src/main/java/com/hushensavani/moviefinder/service*. This will generate output as following:

![Output Console](https://cloud.githubusercontent.com/assets/1409894/21966269/92c6d012-db96-11e6-9712-c0147ccc56b7.png)

Feel free to share your thoughts or issues. Do share your contribution if you have some creative idea to improve this.

[Hushen Savani](mailto:husen.savani1@gmail.com)
