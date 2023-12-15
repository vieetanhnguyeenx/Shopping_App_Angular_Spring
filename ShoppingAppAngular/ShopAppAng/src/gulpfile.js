var gulp = require('gulp');      
var scssPlugin = require('gulp-sass');
var connect = require('gulp-connect');
gulp.task('myStyles', function () {
    gulp.src('sass/*.scss')
        .pipe(scssPlugin())
        .pipe(gulp.dest('css'))
        .pipe(connect.reload());
});

gulp.task('connect', function() {
    connect.server({
        livereload: true
    });
});
gulp.task('watch', function(){
    
})
gulp.task('watchMyStyles', function() {
    gulp.watch('sass/*.scss', ['myStyles']);
});

gulp.task('default', ['watchMyStyles', 'connect']);