package com.example.implementingserversidekotlindevelopment.presentation

import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.RestController

/**
 * 作成済記事記事のコントローラー
 *
 * @property showArticleUseCase 単一記事取得ユースケース
 */
@RestController
@Validated
class ArticleController(val showArticleUseCase: ShowArticleUseCase) {
    /**
     * 単一の作成済記事取得
     *
     * @return
     */
    @GetMapping("/api/articles/{slug}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getArticle(
        @Parameter(description = "記事の slug", required = true, schema = Schema(minLength = 32, maxLength = 32)) @Valid @PathVariable("slug") @Length(min = 32, max = 32) slug: String,
    ): ResponseEntity<SingleArticleResponse> {
        /**
         * 作成済記事の取得
         */
        val createdArticle = showArticleUseCase.execute(slug).getOrElse { TODO() } // エラーハンドリングは後で実装する

        return ResponseEntity(
            SingleArticleResponse(
                article = Article(
                    slug = createdArticle.slug.value,
                    title = createdArticle.title.value,
                    description = createdArticle.description.value,
                    body = createdArticle.body.value,
                )
            ),
            HttpStatus.OK
        )
    }
}
