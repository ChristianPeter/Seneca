/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

var app = angular.module('Viewer', []);

app.config(function($routeProvider,$locationProvider) {
    $routeProvider.
      when('/Seneca/viewer/index.jsp', {controller:ViewerCtrl, templateUrl:'partials/viewer.html'}).
      otherwise({redirectTo:'/Seneca/viewer/index.jsp'});
      
      $locationProvider.html5Mode(true).hashPrefix('!');
      
      console.log('config');
  });


function ViewerCtrl($scope, $routeParams, $location){
    console.log("ViewerCtrl");
    console.log($routeParams.viewport);
}