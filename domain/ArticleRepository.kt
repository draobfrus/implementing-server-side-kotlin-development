/**
 * 作成済記事リポジトリ
 *
 */
interface ArticleRepository {
    /**
     * slug から作成済記事を取得
     *
     * @param slug
     * @return
     */
    fun findBySlug(slug: Slug): Either<FindBySlugError, CreatedArticle> = throw NotImplementedError()

    sealed interface FindBySlugError {
        /**
         * slug に該当する記事が見つからなかった
         *
         * @property slug
         */
        data class NotFound(val slug: Slug) : FindBySlugError
    }
}
