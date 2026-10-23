package graphql;

import com.coxautodev.graphql.tools.SchemaParser;
import graphql.schema.GraphQLSchema;
import graphql.servlet.SimpleGraphQLServlet;

import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = "/graphql")
public class GraphQLEndPoint extends SimpleGraphQLServlet {

    public GraphQLEndPoint() {
        super(buildSchema());
    }

    private static GraphQLSchema buildSchema() {
        try {
            return SchemaParser.newParser()
                    .file("schema.graphql")
                    .resolvers(new Query(), new Mutation())
                    .build()
                    .makeExecutableSchema();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur critique lors de la construction du schéma GraphQL : " + e.getMessage(), e);
        }
    }
}