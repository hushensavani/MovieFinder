# MovieFinder
Finds movies on your storage media such as HDD, pen drive, memory card, mobile storage and fetches the movie details from IMDb. It was never been so easy to explore movies from your local media and prioritize your watch-list by IMDb rating. MovieFinder comes handy for this and other jobs.

![MovieFinder](https://cloud.githubusercontent.com/assets/1409894/21982369/fde7245c-dc11-11e6-89e5-f381734f2675.png)

### Prerequisites
Clone this project and load in your favorite IDE as Maven project. Before you run the project, you need to insert your storage media in your computer and then provide your storage media directory path for which you want to scan the movies, as specified in configuration section.

![Media Path](https://cloud.githubusercontent.com/assets/1409894/21981996/377d2970-dc10-11e6-8c2c-1b82045b54cc.png)

### Configurations
The root directory of your media storage needs to be specified against property **media.root.path** in *config.properties* file available at *src/main/resources*.

![config.properties](https://cloud.githubusercontent.com/assets/1409894/21966239/e9669d9a-db95-11e6-8563-b83690f816dd.png)

### Run
To run the program, the *main()* method is available in service class named **MovieFinderService** at *src/main/java/com/hushensavani/moviefinder/service*. 

![Run](https://cloud.githubusercontent.com/assets/1409894/21966269/92c6d012-db96-11e6-9712-c0147ccc56b7.png)

### Output
After configrations, when run *main()* method from **MovieFinderService** class, it will show output as following list of movies from your storage media sorted by IMDb ratings.

![Output](https://cloud.githubusercontent.com/assets/1409894/21990697/c7ee66fc-dc35-11e6-8d49-4eb96df8a4e9.png)

Feel free to share your thoughts or the issues. Do share your contribution if you have some creative idea to improve this!

[Hushen Savani](mailto:husen.savani1@gmail.com)
