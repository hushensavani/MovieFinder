# MovieFinder
Finds movies on your storage media such as HDD, pen drive, mobile storage etc. and fetches the movie details from IMDB.

### How to run
Clone this project and load in your favorite IDE as Maven project. Before you run the project, you need to do some configuration as below.

### Configurations
The root directory of your media storage needs to be specified against property **media.root.path** in *config.properties* file available at *src/main/resources*.

![config.properties](https://cloud.githubusercontent.com/assets/1409894/21966239/e9669d9a-db95-11e6-8563-b83690f816dd.png)

### Output
To run the program, the main method is available in service class named **MovieFinderService** available at *src/main/java/com/hushensavani/moviefinder/service*. This will generate output as following:

![Output Console](https://cloud.githubusercontent.com/assets/1409894/21966269/92c6d012-db96-11e6-9712-c0147ccc56b7.png)

Hope this helps. Feel free to share your thoughts or issues on this. Do contribute if your hands starts to itch looking at this code. Peace!

[Hushen Savani](mailto:husen.savani1@gmail.com)
