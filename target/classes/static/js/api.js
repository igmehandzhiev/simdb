var app = angular.module('sIMdB', ['infinite-scroll']);
showGenres();
var genres = [];
var page = 1;
var movies = [];
var totalPages = 0;
app.controller('sIMdBController', function ($scope, $http) {

    $scope.submitTitle = function () {

        var valid = $('#searchByTitle')[0].checkValidity();
        if (valid) {
            page = 1;
            movies = [];
            totalPages = 0;
            $scope.getTotalPages();

        }
    };

    $scope.submitDiscovery = function () {
        var valid = $('#discoverMoviesForm')[0].checkValidity();
        if (valid) {
            page = 1;
            movies = [];
            totalPages = 0;
            if (($scope.searchByYear === null || $scope.searchByYear === "") && ($scope.searchByRating === null || $scope.searchByRating === "")) {
                alert("Too many results. Fill at leasy one input!")
            } else {
                $scope.getTotalPages();
            }
        }
    }

    $scope.getTotalPages = function () {
        if ($scope.searchShow) {
            if (totalPages === 0) {
                $http({
                    method: "GET",
                    url: "/movie/search/api/byTitle/pages?t=" + $scope.searchByName
                }).then(function (response) {
                    totalPages = response.data;
                    $scope.search();
                }, function (response) {
                    alert(response.statusText + "\nServer Eror, Please try again");
                });
            }
        } else {
            if (totalPages === 0) {

                let year;
                let rating;
                $scope.searchByYear === null ? year = 0 : year = $scope.searchByYear;
                $scope.searchByRating === null ? rating = 0 : rating = $scope.searchByRating;
                $http({
                    method: "GET",
                    url: "/movie/search/api/discover/pages?" +
                    "y=" + year
                    + "&g=" + genresToString(genres)
                    + "&r=" + rating
                }).then(function (response) {
                    totalPages = response.data;
                    $scope.search();
                }, function (response) {
                    alert(response.statusText + "\nServer Eror, Please try again");
                });
            }
        }
    };

    $scope.search = function () {
        $scope.showTable = true;
        if ($scope.searchShow) {
            if (page <= totalPages) {
                $http({
                    method: "GET",
                    url: "/movie/search/api/byTitle?t=" + $scope.searchByName
                    + "&p=" + page
                }).then(function mySuccess(response) {
                    movies.push.apply(movies, response.data);
                    $scope.movies = movies;
                    if (movies.length === 0) {
                        movies.push({title: "There are no movies of this search"})
                    }
                }, function myError(response) {
                    $scope.searchBusy = true;
                });
            }
        } else {
            if (page <= totalPages) {
                let year;
                let rating;
                $scope.searchByYear === null ? year = 0 : year = $scope.searchByYear;
                $scope.searchByRating === null ? rating = 0 : rating = $scope.searchByRating;

                $http({
                    method: "GET",
                    url: "/movie/search/api/discover?" +
                    "y=" + year
                    + "&g=" + genresToString(genres)
                    + "&r=" + rating
                    + "&p=" + page
                }).then(function mySuccess(response) {
                    movies.push.apply(movies, response.data);
                    $scope.movies = movies;
                    if (movies.length === 0) {
                        movies.push({title: "There are no movies of this search"})
                    }
                }, function myError(response) {
                    $scope.searchBusy = true;
                });
            }
        }
    };

    $scope.sortType = 'title';
    $scope.sortReverse = false;
    $scope.searchMovie = '';


})
;


app.controller('tableController', function ($scope) {
    $scope.nextPage = function () {
        if (page <= totalPages) {
            $scope.searchBusy = true;
            page++;
            $scope.search();
            $scope.searchBusy = false;
        }
    }
});

function genresToString(genres) {
    var genresString = "";
    for (let i in genres) {
        genresString += genres[i] + ",";
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
    app.controller('checkboxController', function ($scope, $http) {

        $http({
            method: "GET",
            url: "/movie/search/api/genres"
        }).then(function mySuccess(response) {
            var genres = response.data;
            var genresArray = [];
            var i, j, chunk = 3, length = genres.length;
            for (i = 0, j = length; i < j; i += chunk) {
                genresArray.push(genres.slice(i, i + chunk));
                // do whatever
            }
            $scope.genresArray = genresArray;

        }, function myError(response) {
            $scope.genresArray = response.statusText;
        });

        $scope.changeGenres = function (genre, active) {
            if (active) {
                genres.push(genre);
            } else {
                genres.splice(genres.indexOf(genre), 1);
            }

        }
    });


}

//Autofocus on show/hide
app.directive('focusOnShow', function ($timeout) {
    return {
        restrict: 'A',
        link: function ($scope, $element, $attr) {
            if ($attr.ngShow) {
                $scope.$watch($attr.ngShow, function (newValue) {
                    if (newValue) {
                        $timeout(function () {
                            $element[0].focus();
                        }, 0);
                    }
                })
            }
            if ($attr.ngHide) {
                $scope.$watch($attr.ngHide, function (newValue) {
                    if (!newValue) {
                        $timeout(function () {
                            $element[0].focus();
                        }, 0);
                    }
                })
            }

        }
    };
});
window.onscroll = function () {
    scrollFunction()
};

function scrollFunction() {
    if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
        document.getElementById("goToTop").style.display = "block";
    } else {
        document.getElementById("goToTop").style.display = "none";
    }
}

// When the user clicks on the button, scroll to the top of the document
function topFunction() {
    document.body.scrollTop = 0; // For Chrome, Safari and Opera
    document.documentElement.scrollTop = 0; // For IE and Firefox
}