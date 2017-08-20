var app = angular.module('sIMdB', []);
showGenres();
var genres = [];

app.controller('sIMdBController', function ($scope, $http) {

    $scope.search = function () {
        if ($scope.advanced === false) {
            $http({
                method: "GET",
                url: "/movie/search/byTitle?t=" + $scope.searchByName
            }).then(function mySuccess(response) {
                $scope.movies = response.data;
            }, function myError(response) {
                $scope.movies = response.statusText;
            });
        } else {
            let year;
            let rating;
            $scope.searchByYear === null ? year = 0 : year = $scope.searchByYear;
            $scope.searchByRating === null ? rating = 0 : rating = $scope.searchByRating;


            if (genres.length === 0) {
                $http({
                    method: "GET",
                    url: "/movie/search/tyr?" +
                    "t=" + $scope.searchByName +
                    "&y=" + year +
                    "&r=" + rating
                }).then(function mySuccess(response) {
                    $scope.movies = response.data;
                }, function myError(response) {
                    $scope.movies = response.statusText;
                });
            } else {
                if (year === 0 && rating === 0) {
                    $http({
                        method: "GET",
                        url: "/movie/search/byGenres?" +
                        "g=" + genresToString(genres)
                    }).then(function mySuccess(response) {
                        $scope.movies = response.data;
                    }, function myError(response) {
                        $scope.movies = response.statusText;
                    });
                } else {
                    $http({
                        method: "GET",
                        url: "/movie/search/tygr?" +
                        "t=" + $scope.searchByName +
                        "&y=" + year +
                        "&g=" + genresToString(genres) +
                        "&r=" + rating
                    }).then(function mySuccess(response) {
                        $scope.movies = response.data;
                    }, function myError(response) {
                        $scope.movies = response.statusText;
                    });
                }
            }
        }
    };

    $scope.sortType = 'title';
    $scope.sortReverse = false;
    $scope.searchMovie = '';


});


function genresToString(genres) {
    var genresString = "";
    for (i in genres) {
        genresString += genres[i].type + ",";
    }
    genresString = genresString.slice(0, -1);
    return genresString;
}

// app.controller('moviesDisplay', function ($scope) {
//     function showMovie(title) {
//         $scope.movie.poster;
//
//     }
// });

function showGenres() {
    app.controller('checkboxController', function ($scope) {
        $scope.genresArray = [[{
            type: "Drama",
            value: false
        }, {
            type: "Action",
            value: false
        }, {
            type: "Biography",
            value: false
        }, {
            type: "Crime",
            value: false
        }], [{
            type: "Thriller",
            value: false
        }, {
            type: "Adventure",
            value: false
        }, {
            type: "Comedy",
            value: false
        }, {
            type: "Romance",
            value: false
        }], [{
            type: "Family",
            value: false
        }, {
            type: "Music",
            value: false
        }, {
            type: "Mystery",
            value: false
        }, {
            type: "History",
            value: false
        }], [{
            type: "Sport",
            value: false
        }, {
            type: "Musical",
            value: false
        }, {
            type: "Sci-Fi",
            value: false
        }], [{
            type: "War",
            value: false
        }, {
            type: "Fantasy",
            value: false
        }, {
            type: "Documentary",
            value: false
        }, {
            type: "Horror",
            value: false
        }], [{
            type: "Animation",
            value: false
        }, {
            type: "Western",
            value: false
        }, {
            type: "Film-Noir",
            value: false
        }]];
        $scope.changeGenres = function (genre, active) {
            if (active) {
                genres.push(genre);
            } else {
                genres.splice(genres.indexOf(genre), 1);
            }
            genres.sort(function (a, b) {
                var nameA = a.type;
                var nameB = b.type;
                if (nameA < nameB) {
                    return -1;
                }
                if (nameA > nameB) {
                    return 1;
                }

                return 0;
            });

        }
    });

}