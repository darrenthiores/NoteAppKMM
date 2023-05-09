import Foundation
import shared

class AppModule {
    init() {
        @Provider var sqlDriver = DatabaseDriverFactory().createDriver()
        @Provider var database: NoteDatabase = DatabaseFactory().createDatabase(driver: sqlDriver)
        @Provider var noteDataSource: NoteDataSource = NoteDataSourceImpl(db: database)
    }
}
