@RestController
public class UrlController {

    @Autowired
    private UrlService urlService;

    // POST /api/shorten
    @PostMapping("/api/shorten")
    public String shortenUrl(@RequestBody String originalUrl) {
        return urlService.shortenUrl(originalUrl);
    }

    // GET /{code}
    @GetMapping("/{code}")
    public ResponseEntity<Void> redirect(@PathVariable String code) {
        String originalUrl = urlService.getOriginalUrl(code);
        if (originalUrl == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.FOUND)
        .location(URI.create(originalUrl))
        .build();
    }
}